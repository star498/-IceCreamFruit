package com.example.estrellabarrientosmogollon.icecreamfruit.config.base.filterChooser;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


import com.example.estrellabarrientosmogollon.icecreamfruit.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class FilterChooserBottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener,  FilterChoserAdapter.OnItemClickListener {

    private String dialogTitle;
    private List<Object> items = new ArrayList<>();
    private Object object;
    private List<String> singleItems = new ArrayList<>();
    private int positionSelected;
    private CallbackFilterChooserBottomDialog callbackFilter;
    private ArrayAdapter<String> adapter;
    private CheckBox checkBox;

    private void setSingleItems() {
        for(Object o: this.items)singleItems.add(o.toString());
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public void setItems(List<Object> items, Object object) {
        this.items.addAll(items);
        this.object = object;
        setSingleItems();
        setPositionSelected();
    }

    private void setPositionSelected() {
        if(object ==null)this.positionSelected = 0;
        else this.positionSelected = items.indexOf(object);
    }

    public void setItems(Enum[] objects, Enum object) {
        items.addAll(Arrays.asList(objects));
        this.object = object;
        setSingleItems();
        setPositionSelected();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.dialogbasefilter, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                double SLIDEOFFSETHIDEN = -0.9f;

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    switch (newState) {

                        case BottomSheetBehavior.STATE_COLLAPSED: {

                            Log.d("BSB", "collapsed");
                        }
                        case BottomSheetBehavior.STATE_SETTLING: {

                            Log.d("BSB", "settling");
                        }
                        case BottomSheetBehavior.STATE_EXPANDED: {

                            Log.d("BSB", "expanded");
                        }
                        case BottomSheetBehavior.STATE_HIDDEN: {

                            Log.d("BSB", "hidden");
                        }
                        case BottomSheetBehavior.STATE_DRAGGING: {

                            Log.d("BSB", "dragging");
                        }
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    if (SLIDEOFFSETHIDEN >= slideOffset) dismiss();
                }
            });
        }
        TextView textViewTitulo = (TextView) contentView.findViewById(R.id.textViewTitulo);
        textViewTitulo.setText(dialogTitle);
        Button buttonAceptar = (Button) contentView.findViewById(R.id.btn_aceptar);
        buttonAceptar.setOnClickListener(this);
        Button buttonCancelar = (Button) contentView.findViewById(R.id.btn_cancelar);
        buttonCancelar.setOnClickListener(this);
        RecyclerView listView = (RecyclerView) contentView.findViewById(R.id.reciclador);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setAdapter(new FilterChoserAdapter(singleItems, positionSelected, this));
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_aceptar) {
            if (callbackFilter != null) callbackFilter.onAceptarBottomDialog(object);
            dismiss();

        } else if (i == R.id.btn_cancelar) {
            if (callbackFilter != null) callbackFilter.onCancelarBottomDialog();
            dismiss();

        } else {
        }
    }


    public void setCallbackFilter(CallbackFilterChooserBottomDialog callbackFilter) {
        this.callbackFilter = callbackFilter;
    }

    @Override
    public void onItemClick(View view, int itemIndex) {
        if(checkBox!=null)checkBox.setChecked(false);
        checkBox = (CheckBox) view.findViewById(R.id.checkTipos);
        if(!checkBox.isChecked()){
            checkBox.setChecked(true);
            object = items.get(itemIndex);
        }
    }

    @Override
    public void onItemSelect(View view, int itemIndex) {
        checkBox = (CheckBox) view.findViewById(R.id.checkTipos);
    }
}
