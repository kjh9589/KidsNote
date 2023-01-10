package com.kjs.kidsnote.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kjs.kidsnote.databinding.ActivityMainBinding
import com.kjs.kidsnote.presentation.common.CommonActivity
import com.kjs.kidsnote.presentation.main.adapter.MainPicsumAdapter
import com.kjs.kidsnote.presentation.main.detail_picsum.DetailPicsumActivity
import com.kjs.kidsnote.util.Constants
import com.kjs.kidsnote.util.Constants.PICSUM_LIMIT
import com.kjs.kidsnote.util.decoration.VerticalSpaceDecoration
import com.kjs.model.picsum.PicsumModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : CommonActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    private val detailPicsumRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        viewModel.reloadLike()
    }

    private val adapter by lazy {
        MainPicsumAdapter(
            object : MainPicsumAdapter.MainPicsumListener {
                override fun moveToMain(item: PicsumModel, position: Int) {
                    val intent = Intent(this@MainActivity, DetailPicsumActivity::class.java).apply {
                        putExtra(DetailPicsumActivity.DETAIL_PICSUM_OFFSET, (position / PICSUM_LIMIT) + 1)
                        putExtra(DetailPicsumActivity.DETAIL_PICSUM_POSITION, position)
                    }
                    detailPicsumRegister.launch(intent)
                }

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
            viewModel.getList()
        }
    }

    override fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = null
        recyclerView.addItemDecoration(VerticalSpaceDecoration(Constants.COMMON_RECYCLERVIEW_VERTICAL_SPACE))

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getList()
                }
            }
        })
    }

    override fun bindViews() {
        lifecycleScope.launchWhenStarted {
            viewModel.picsumImageListStateFlow.collectLatest { list ->
                val recyclerViewState = binding.recyclerView.layoutManager?.onSaveInstanceState()

                adapter.submitList(list) {
                    binding.recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
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