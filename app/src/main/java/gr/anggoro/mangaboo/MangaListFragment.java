package gr.anggoro.mangaboo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MangaListFragment extends FragmentControl {
    private ProgressBar progressBar;

    MangaListFragment(){
        super(0);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       new Description(this).execute();
    }

    private static class Description extends AsyncTask<Void, Void, Void> {
        private ArrayList<String> titleList = new ArrayList<>();
        private WeakReference<MangaListFragment> fragmentWeakReference;

        Description(MangaListFragment fragment){
            fragmentWeakReference = new WeakReference<>(fragment);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MangaListFragment fragment = fragmentWeakReference.get();
            if (fragment == null || fragment.isRemoving() || fragment.isDetached()){
                return;
            }

            ProgressBar progressBar = fragment.getView().findViewById(R.id.progress_bar);
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String url = "https://www.yudiz.com/blog/";

            try {
                Document blog = Jsoup.connect(url).get();
                Elements dataSize = blog.select("div[class=author-date]");
                int elementSize = dataSize.size();

                for (int i = 0; i < elementSize; i++){
                    Elements authorName = blog.select("span[class=vcard author post-author test]").select("a").eq(i);
                    String author = authorName.text();

                    Elements blogUpload = blog.select("span[class=post-date updated]").eq(i);
                    String uploadDate = blogUpload.text();

                    Elements blogTitle = blogUpload.select(("h2[class=entry-title]")).select("a").eq(i);
                    String title = blogTitle.text();

                    titleList.add(author);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            MangaListFragment fragment = fragmentWeakReference.get();
            if (fragment == null || fragment.isRemoving() || fragment.isDetached()){
                return;
            }

            RecyclerView recyclerView = fragment.getView().findViewById(R.id.act_recycler);
            DataAdapter dataAdapter = new DataAdapter(fragment.getActivity(), titleList, titleList, titleList);
            RecyclerView.LayoutManager lm = new LinearLayoutManager(fragment.getActivity());

            recyclerView.setLayoutManager(lm);
            recyclerView.setAdapter(dataAdapter);

            ProgressBar progressBar = fragment.getView().findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);
        }
    }
}
