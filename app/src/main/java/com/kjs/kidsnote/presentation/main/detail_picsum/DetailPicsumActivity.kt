package com.kjs.kidsnote.presentation.main.detail_picsum

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.kjs.kidsnote.R
import com.kjs.kidsnote.databinding.ActivityDetailPicsumBinding
import com.kjs.kidsnote.presentation.common.CommonActivity
import com.kjs.kidsnote.presentation.main.detail_picsum.adapter.DetailPicsumAdapter
import com.kjs.kidsnote.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailPicsumActivity : CommonActivity() {

    companion object {
        const val DETAIL_PICSUM_OFFSET = "DETAIL_PICSUM_OFFSET"
        const val DETAIL_PICSUM_POSITION = "DETAIL_PICSUM_POSITION"
    }

    private val binding by lazy { ActivityDetailPicsumBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<DetailPicsumViewModel>()

    private val adapter by lazy {
        DetailPicsumAdapter(
            object : DetailPicsumAdapter.DetailPicsumListener {
                override fun setOnLike(id: String, isLike: Boolean, position: Int) {
                    viewModel.like(id, isLike, position)
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        bindViews()

        if (savedInstanceState == null) {
            viewModel.getInitList(intent.getIntExtra(DETAIL_PICSUM_OFFSET, 1))
        }
    }

    override fun initViews() {
        initButtons()
        initViewPager()
    }

    private fun initButtons() = with(binding) {
        backImageView.setOnClickListener {
            finish()
        }
    }

    private fun initViewPager() = with(binding) {
        detailPicsumViewPager.adapter = adapter

        detailPicsumViewPager.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    if (!viewModel.lock && position == 0) {
                        viewModel.lock = true
                        viewModel.getPrevList()
                    }

                    if (!viewModel.lock && position == this@DetailPicsumActivity.adapter.itemCount - 1) {
                        viewModel.lock = true
                        viewModel.getNextList()
                    }
                }
            })
            offscreenPageLimit = 2
        }

        detailPicsumViewPager.setPageTransformer { page, position ->
            page.translationX =
                -resources.getDimension(R.dimen.common_horizontal_viewpager_clip) * position
        }
    }

    override fun bindViews() {
        lifecycleScope.launchWhenStarted {
            viewModel.picsumImageListStateFlow.collectLatest { list ->
                adapter.submitList(list) {
                    if (list.isNotEmpty() && !viewModel.isInit) {
                        binding.detailPicsumViewPager.setCurrentItem(
                            intent.getIntExtra(
                                DETAIL_PICSUM_POSITION, 0
                            ) - ((intent.getIntExtra(DETAIL_PICSUM_OFFSET, 1) - 1) * Constants.PICSUM_LIMIT),
                            false
                        )
                        viewModel.isInit = true
                    }
                    viewModel.lock = false
                }
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                if (it) {
                    showLoadingDialog()
                } else {
                    hideLoadingDialog()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.clearHolder()
    }
}