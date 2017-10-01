package com.tutorials.hp.gridviewpaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

/*
- Our MainActivity class.
- Our widgets will be a GridView and two buttons: next and previous.
- We instantiate our Paginator here and page data.
- We toggle button states depending on current page.
 */
public class MainActivity extends AppCompatActivity {


    GridView gridView;
    Button nextBtn, prevBtn;
    Paginator p = new Paginator();
    private int totalPages =p.getTotalPages();
    private int currentPage = 0;
    CustomAdapter adapter;


    /*
    - When activity is created
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeViews();
        this.bindData(currentPage);
        prevBtn.setEnabled(false);

        //NAVIGATE
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage += 1;
                bindData(currentPage);
                toggleButtons();
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage -= 1;
                bindData(currentPage);
                toggleButtons();
            }
        });
    }

    /*
    - Initialize GridView and buttons.
     */
    private void initializeViews()
    {
        gridView= (GridView) findViewById(R.id.gridView);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        prevBtn = (Button) findViewById(R.id.prevBtn);
    }

    /*
    - Bind data to GridView.
     */
    private void bindData(int page) {
        adapter=new CustomAdapter(this,p.getCurrentGalaxys(page));
        gridView.setAdapter(adapter);
    }


    /*
    - Toggle button states
     */
    private void toggleButtons() {
        //SINGLE PAGE DATA
        if (totalPages <= 1) {
            nextBtn.setEnabled(false);
            prevBtn.setEnabled(false);
        }
        //LAST PAGE
        else if (currentPage == totalPages) {
            nextBtn.setEnabled(false);
            prevBtn.setEnabled(true);
        }
        //FIRST PAGE
        else if (currentPage == 0) {
            prevBtn.setEnabled(false);
            nextBtn.setEnabled(true);
        }
        //SOMEWHERE IN BETWEEN
        else if (currentPage >= 1 && currentPage <= totalPages) {
            nextBtn.setEnabled(true);
            prevBtn.setEnabled(true);
        }

    }
}
