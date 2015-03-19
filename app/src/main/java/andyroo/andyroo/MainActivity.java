package andyroo.andyroo;

import java.util.Locale;
import 	java.io.IOException;
import java.io.File;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.media.MediaPlayer;



public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, MediaPlayer.OnCompletionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = new MediaPlayer();
        mp.setOnCompletionListener(this);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    /**
     * On Song Playing completed
     * if repeat is ON play same song again
     * if shuffle is ON play random song
     * */
    @Override
    public void onCompletion(MediaPlayer arg0) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void playButtonPressed(View view) {
        ImageButton playButton = (ImageButton) view;
        int id = playButton.getId();
        if (id == R.id.playButton) {
            try {
                AssetFileDescriptor afd = this.getResources().openRawResourceFd(R.raw.booster_buddy);
                mp.setDataSource(afd.getFileDescriptor());
                afd.close();

                mp.prepare();
                mp.start();


                // Changing Button Image to pause image
                playButton.setImageResource(R.drawable.pause_button);

                // set Progress bar values
                //songProgressBar.setProgress(0);
                //songProgressBar.setMax(100);

                // Updating progress bar
                //updateProgressBar();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (id == R.id.playButton2) {
            int x = 1;
        }
        else if (id == R.id.playButton3) {
            int x = 1;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            Bundle args = getArguments();
            if (args.getInt(ARG_SECTION_NUMBER) == 1)
                rootView =  inflater.inflate(R.layout.fragment_main, container, false);
            else if (args.getInt(ARG_SECTION_NUMBER) == 2) {
                rootView = inflater.inflate(R.layout.fragment_music, container, false);
                final ImageButton button = (ImageButton) rootView.findViewById(R.id.button_itunes);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://itunes.apple.com/us/artist/andyroo/id567828684"));
                        startActivity(browserIntent);
                    }
                });
            }
            else if (args.getInt(ARG_SECTION_NUMBER) == 3) {
                rootView = inflater.inflate(R.layout.fragment_videos, container, false);
                final ImageButton button = (ImageButton) rootView.findViewById(R.id.button_andyroo_dot_com);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.andyroo.com"));
                        startActivity(browserIntent);
                    }
                });
                final ImageButton button2 = (ImageButton) rootView.findViewById(R.id.button_you_tube);
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/channel/UCsgoouwNAL_0ikhk-ImbT4w"));
                        startActivity(browserIntent);
                    }
                });
                /*
                final LinearLayout linearLayout04 = (LinearLayout) rootView.findViewById(R.id.LinearLayout04);
                final LinearLayout linearLayout05 = (LinearLayout) linearLayout04.findViewById(R.id.LinearLayout05);
                final ImageButton playButton = (ImageButton) linearLayout05.findViewById(R.id.play_button);
                playButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/channel/UCsgoouwNAL_0ikhk-ImbT4w"));
                        startActivity(browserIntent);
                    }
                });
                */
                final WebView webView1 = (WebView) rootView.findViewById(R.id.webView1);
                webView1.setWebChromeClient(new WebChromeClient());
                WebSettings webSettings = webView1.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setUseWideViewPort(true);
                webSettings.setLoadWithOverviewMode(true);

                webView1.loadUrl("http://www.youtube.com/watch?v=V5UUHbRzjek");
                webView1.setWebViewClient(new WebViewClient());

                final WebView webView2 = (WebView) rootView.findViewById(R.id.webView2);
                webView2.setWebChromeClient(new WebChromeClient());
                webSettings = webView2.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setUseWideViewPort(true);
                webSettings.setLoadWithOverviewMode(true);

                webView2.loadUrl("http://www.youtube.com/watch?v=5smoXLztStE");
                webView2.setWebViewClient(new WebViewClient());
            }
            else if (args.getInt(ARG_SECTION_NUMBER) == 4)
                rootView =  inflater.inflate(R.layout.fragment_bio, container, false);


            return rootView;
        }
    }
}
