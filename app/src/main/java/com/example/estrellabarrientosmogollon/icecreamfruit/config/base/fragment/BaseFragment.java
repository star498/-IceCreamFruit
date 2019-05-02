package com.example.estrellabarrientosmogollon.icecreamfruit.config.base.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.example.estrellabarrientosmogollon.icecreamfruit.R;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BaseView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by @stevecampos on 14/02/2018.
 */

public abstract class BaseFragment<V extends BaseView<P>, P extends BaseFragmentPresenter<V>, L> extends Fragment implements BaseView<P> {


    protected abstract String getLogTag();

    protected abstract P getPresenter();

    protected abstract V getBaseView();

    //protected abstract Bundle getExtrasInf();

    protected P presenter;

    protected abstract View inflateView(LayoutInflater inflater, ViewGroup container);

    protected abstract ViewGroup getRootLayout();

    protected abstract ProgressBar getProgressBar();

    public BaseFragment() {
    }

    /*public static <T extends AppCompatActivity> Intent getStartIntent(Context context, Class<T> tClass) {
        return new Intent(context, tClass);
    }*/

    private ProgressBar progressBar;
    protected L listener;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        Log.d(getLogTag(), "onAttach");
        super.onAttach(context);
        if (context instanceof BaseFragmentListener) {
            listener = (L) context;
        } else {
            throw new ClassCastException(
                    context.toString() + "must implement BaseFragmentListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(getLogTag(), "onCreate");
        super.onCreate(savedInstanceState);
        setupPresenter();
        if (presenter != null) presenter.onCreate();
        if (getArguments() != null) {
            /*mIdCargaCurso = getArguments().getInt(ARG_ID_CARGA_CURSO);
            mIdCurso = getArguments().getInt(ARG_ID_CURSO);
            Log.d(TAG, "mIdCargaCurso: " + mIdCargaCurso);
            Log.d(TAG, "mIdCurso: " + mIdCurso);*/
        }
    }

    private void setupPresenter() {
        if (presenter == null) {
            presenter = getPresenter();
        }
        setPresenter(presenter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(getLogTag(), "onCreateView");
        if (presenter != null) presenter.onCreateView();
        return inflateView(inflater, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(getLogTag(), "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setupProgressBar();
        //setRetainInstance(true);
        if (presenter != null) presenter.onViewCreated();
        if (presenter != null) presenter.setExtras(getArguments());
    }


    private void setupProgressBar() {
        progressBar = getProgressBar();
    }

    @Override
    public void onStart() {
        Log.d(getLogTag(), "onResume");
        super.onStart();
        if (presenter != null) presenter.onStart();
    }

    @Override
    public void onResume() {
        Log.d(getLogTag(), "onResume");
        super.onResume();
        if (presenter != null) presenter.onResume();
    }

    @Override
    public void onPause() {
        Log.d(getLogTag(), "onPause");
        super.onPause();
        if (presenter != null) presenter.onPause();
    }

    @Override
    public void onStop() {
        Log.d(getLogTag(), "onStop");
        super.onStop();
        if (presenter != null) presenter.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(getLogTag(), "onDestroyView");
        super.onDestroyView();
        unbinder.unbind();
        if (presenter != null) presenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(getLogTag(), "onDestroy");
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }


    @Override
    public void onDetach() {
        Log.d(getLogTag(), "onDetach");
        listener = null;
        super.onDetach();
        if (presenter != null) presenter.onDetach();
    }


    @Override
    public void setPresenter(P presenter) {
        if (presenter != null) {
            presenter.attachView(getBaseView());
        }
    }

    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }


    public void showMessage(CharSequence error) {
        Snackbar.make(getRootLayout(), error, Snackbar.LENGTH_LONG).show();
    }

    public void showImportantMessage(CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(R.string.dialog_title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void showFinalMessage(CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        getActivity().finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Crea un Diálogo con una lista de selección simple
     */
    public void showListSingleChooser(String dialogTitle, final List<Object> items, int positionSelected) {
        if (items.isEmpty()) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        int size = items.size();
        final CharSequence[] singleItems = new CharSequence[size];

        for (int i = 0; i < size; i++) {
            singleItems[i] = items.get(i).toString();
        }

        if (positionSelected >= items.size()) {
            positionSelected = -1;
        }

        builder.setTitle(dialogTitle)
                .setSingleChoiceItems(singleItems, positionSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getTag(), "setSingleChoiceItems onClick i: " + which);
                    }
                })
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getTag(), "setPositiveButton onClick i: " + which);
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        if (selectedPosition != -1) {
                            Object objectSelected = items.get(selectedPosition);
                            if (presenter != null){
                                presenter.onSingleItemSelected(objectSelected, selectedPosition);
                            }
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(getTag(), "setNegativeButton onClick i: " + which);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }





}
