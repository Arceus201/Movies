package com.example.movies.screen.detail

import android.view.View
import androidx.core.os.bundleOf
import com.example.movies.data.model.Movie
import com.example.movies.databinding.FragmentDetailBinding
import com.example.movies.utlis.base.BaseFragment
import com.example.movies.utlis.ext.goBackFragment
import com.example.movies.utlis.ext.loadImageCircleWithUrl
import com.example.movies.utlis.ext.loadImageWithUrl
import com.example.movies.utlis.ext.notNull


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private var mMovie: Movie? = null
    override fun initView(view: View) {
        binding.buttonImageBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                goBackFragment()
            }
        })
    }

    override fun initData() {
        arguments?.run {
            mMovie = getParcelable(ARGUMENT_MOVIE)
        }
        mMovie?.notNull {
            binding.apply {
                imageBackDrop.loadImageWithUrl(it.backDropImage)
                imageMovie.loadImageCircleWithUrl(it.urlImage)
                textTitle.text = it.title
                textDescription.text = it.overView
                textRatting.text = it.vote.toString()
                textTotalReview.text = it.voteCount.toString()
            }
        }
    }

    override fun handleEvent() {
        //TODO("Not yet implemented")
    }


    companion object {
        private const val ARGUMENT_MOVIE = "ARGUMENT_MOVIE"

        fun newInstance(movie: Movie?) = DetailFragment().apply {
            arguments = bundleOf(ARGUMENT_MOVIE to movie)
        }
    }

}
