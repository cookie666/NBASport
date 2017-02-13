package com.cookie.NBASport.ui.main;/**
 * Created by Chen Lin Jiang on 2017/1/18.
 */

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.cookie.NBASport.R;
import com.cookie.NBASport.base.BaseFragment;
import com.cookie.NBASport.bean.NewsItemEntity;
import com.cookie.NBASport.bean.VideoInfo;
import com.cookie.NBASport.di.FragmentComponent;
import com.cookie.NBASport.http.NetApi;
import com.cookie.NBASport.http.NetCallback;
import com.cookie.NBASport.http.retrofit.RetrofitClient;
import com.cookie.NBASport.presenter.TopNewsPresenter;
import com.cookie.NBASport.ui.adpter.CommonAdapter;
import com.cookie.NBASport.ui.adpter.ViewHolder;
import com.cookie.NBASport.view.TopNewsView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-18
 */
public class TopNewsFragment extends BaseFragment<TopNewsPresenter> implements TopNewsView {

    @BindView(R.id.materialRefresh)
    MaterialRefreshLayout materialRefresh;

    @BindView(R.id.listViewContent)
    ListView listViewContent;
    private List<NewsItemEntity.NewsItem> listDatas = new ArrayList<NewsItemEntity.NewsItem>();
    private CommonAdapter commonAdapter;
    public static final String TYPE = "type";
    private String type = NetApi.BANNER;

    @Override
    protected int getContentView() {
        return R.layout.fragment_today_news;
    }

    @Override
    protected void injectFragment(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void init() {
        type = getArguments().getString(TYPE);

        if(!TextUtils.isEmpty(type)){
            mPresenter.getIndexNews(type,this);
        }
        switch (type){
            case NetApi.BANNER:
                commonAdapter = new CommonAdapter(listDatas,context,R.layout.item_list_news_normal) {
                    @Override
                    public void getConvert(ViewHolder viewHolder, Object data) {
                        TextView timeTextView = viewHolder.getView(R.id.tvBannerTime);
                        TextView titleTextView = viewHolder.getView(R.id.tvBannerTitle);
                        SimpleDraweeView iv = viewHolder.getView(R.id.ivBannerImg);

                        NewsItemEntity.NewsItem newsItem = (NewsItemEntity.NewsItem)data;

                        Uri uri = Uri.parse(newsItem.imgurl);
                        iv.setImageURI(uri);

                        titleTextView.setText(newsItem.title);
                        timeTextView.setText(newsItem.pub_time);
                    }
                };
                break;
            case NetApi.DEPTH:
                commonAdapter = new CommonAdapter(listDatas,context,R.layout.item_list_news_video) {
                    @Override
                    public void getConvert(ViewHolder viewHolder, Object data) {
                        final JCVideoPlayerStandard videoPlayer = viewHolder.getView(R.id.vpVideo);
                        ImageView ivGoto = viewHolder.getView(R.id.ivGoto);
                        final NewsItemEntity.NewsItem newsItem = (NewsItemEntity.NewsItem)data;
                        ivGoto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                NewDetailActivity.open(context,newsItem.index);
                            }
                        });

                        videoPlayer.setUp("", JCVideoPlayerStandard.SCREEN_LAYOUT_LIST,newsItem.title);
                        if(TextUtils.isEmpty(newsItem.realUrl)){
                            RetrofitClient.getVideoUrl(newsItem.vid, new NetCallback<VideoInfo>() {
                                @Override
                                public void onSuccess(VideoInfo videoInfo) {
                                    if (videoInfo.vl.vi != null && videoInfo.vl.vi.size() > 0) {
                                        String vid = videoInfo.vl.vi.get(0).vid;
                                        String vkey = videoInfo.vl.vi.get(0).fvkey;
                                        String url = videoInfo.vl.vi.get(0).ul.ui.get(0).url + vid + ".mp4?vkey=" + vkey;
                                        newsItem.realUrl = url;

                                        videoPlayer.setUp(url,JCVideoPlayerStandard.SCREEN_LAYOUT_LIST,newsItem.title);
                                    }
                                }

                                @Override
                                public void onFail(String msg) {
                                    Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                                }
                            });
                        }else{
                            videoPlayer.setUp(newsItem.realUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,newsItem.title);
                        }
                        videoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        Glide.with(TopNewsFragment.this).load(newsItem.imgurl).skipMemoryCache(true).into(videoPlayer.thumbImageView);
                    }
                };
                break;
        }

        listViewContent.setAdapter(commonAdapter);
        listViewContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewDetailActivity.open(context,listDatas.get(position).index);
            }
        });


        materialRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mPresenter.getIndexNews(type,TopNewsFragment.this);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                mPresenter.getLoadMore(type,TopNewsFragment.this);
            }
        });
    }

    @Override
    public void getIndexData(List<NewsItemEntity.NewsItem> list) {
        listDatas.clear();
        listDatas.addAll(list);
        commonAdapter.notifyDataSetChanged();
        materialRefresh.finishRefresh();
    }

    @Override
    public void getLoadMoreData(List<NewsItemEntity.NewsItem> list) {
        materialRefresh.finishRefreshLoadMore();
        listDatas.addAll(list);
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String msg) {
        materialRefresh.finishRefresh();
        materialRefresh.finishRefreshLoadMore();
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}

