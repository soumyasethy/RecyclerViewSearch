package com.soumyasethy.mapprr;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 29/12/17.
 */

public class Filter extends AAH_FabulousFragment {
  static String sort, order;

  public static Filter newInstance(String curr_sort, String curr_order) {
    sort = curr_sort;
    order = curr_order;
    Filter f = new Filter();
    return f;
  }

  @Override

  public void setupDialog(Dialog dialog, int style) {
    View contentView = View.inflate(getContext(), R.layout.filter, null);
    RelativeLayout rl_content = (RelativeLayout) contentView.findViewById(R.id.rl_content);
    LinearLayout ll_buttons = (LinearLayout) contentView.findViewById(R.id.footer);
    contentView.findViewById(R.id.Close).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeFilter("closed");
      }
    });
    Button stars = (Button) contentView.findViewById(R.id.star);
    Button forks = (Button) contentView.findViewById(R.id.forks);
    Button updated = (Button) contentView.findViewById(R.id.updated);
    Button desc = (Button) contentView.findViewById(R.id.desc);
    Button asc = (Button) contentView.findViewById(R.id.asc);

    ShapeDrawable shapedrawable = new ShapeDrawable();
    shapedrawable.setShape(new RectShape());
    shapedrawable.getPaint().setColor(Color.RED);
    shapedrawable.getPaint().setStrokeWidth(05f);
    shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
    if (sort == "stars") {
      stars.setBackground(shapedrawable);
    } else if (sort == "forks") {
      stars.setBackground(shapedrawable);
    } else if (sort == "updated") {
      stars.setBackground(shapedrawable);
    }


    stars.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeFilter("stars");
      }
    });

    forks.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeFilter("forks");
      }
    });
    updated.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeFilter("updated");
      }
    });
    desc.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeFilter("desc");
      }
    });
    asc.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeFilter("asc");
      }
    });

    //params to set
    setAnimationDuration(200); //optional; default 500ms
    setPeekHeight(300); // optional; default 400dp
    setCallbacks((Callbacks) getActivity()); //optional; to get back result
    //setAnimationListener((AnimationListener) getActivity()); //optional; to get animation callbacks
    setViewgroupStatic(ll_buttons); // optional; layout to stick at bottom on slide
    //setViewPager(vp_types); //optional; if you use viewpager that has scrollview
    setViewMain(rl_content); //necessary; main bottomsheet view
    setMainContentView(contentView); // necessary; call at end before super
    super.setupDialog(dialog, style); //call super at last


  }

}
