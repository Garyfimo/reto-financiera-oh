package com.garyfimo.retofinancieraoh.ui.clients.add;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.garyfimo.retofinancieraoh.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NewClientFragment extends Fragment implements View.OnClickListener, NewClientView {

    @BindView(R.id.layout_loading)
    LinearLayout llLoading;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_lastname)
    EditText etLastname;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.et_birthday)
    EditText etBirthday;
    @BindView(R.id.btn_add)
    Button btnAdd;

    private NewClientPresenter presenter;

    private OnNewClientFragmentInteractionListener mListener;

    public NewClientFragment() {
        // Required empty public constructor
    }

    public static NewClientFragment newInstance() {
        return new NewClientFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewClientPresenter();
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_client, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEditTextListener();
    }

    private void initEditTextListener() {
        etBirthday.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewClientFragmentInteractionListener) {
            mListener = (OnNewClientFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.btn_add)
    public void onAddButtonClicked() {
        presenter.addClient(getName(),
                getLastname(),
                getAge(),
                getBirthday());
        mListener.onClientAdded();
    }

    @Override
    public void nameEmpty() {
        etName.setError(getString(R.string.field_can_not_be_empty));
    }

    @Override
    public void lastnameEmpty() {
        etLastname.setError(getString(R.string.field_can_not_be_empty));
    }

    @Override
    public void ageEmpty() {
        etAge.setError(getString(R.string.field_can_not_be_empty));
    }

    @Override
    public void birthdayEmpty() {
        etBirthday.setError(getString(R.string.field_can_not_be_empty));
    }

    @Override
    public void setBirthdayText(String birthdayString) {
        etBirthday.setText(birthdayString);
    }

    private String getName() {
        return etName.getText().toString();
    }

    private String getLastname() {
        return etLastname.getText().toString();
    }

    private String getAge() {
        return etAge.getText().toString();
    }

    private String getBirthday() {
        return etBirthday.getText().toString();
    }

    private DatePickerDialog getDatePickerDialog() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
            presenter.convertDateFromNumbers(year, month, dayOfMonth);
            getDatePickerDialog().dismiss();
        }, newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        return dpd;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.et_birthday) {
            getDatePickerDialog().show();
        }
    }

    public interface OnNewClientFragmentInteractionListener {
        void onClientAdded();
    }
}
