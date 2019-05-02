package com.garyfimo.retofinancieraoh.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.garyfimo.retofinancieraoh.BaseActivity;
import com.garyfimo.retofinancieraoh.MainActivity;
import com.garyfimo.retofinancieraoh.R;
import com.garyfimo.retofinancieraoh.ui.util.TextWatcherAdapter;
import com.garyfimo.retofinancieraoh.ui.util.biometric.BiometricCallback;
import com.garyfimo.retofinancieraoh.ui.util.biometric.BiometricManager;
import com.garyfimo.retofinancieraoh.ui.util.customview.SmsCodeView;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView, BiometricCallback {

    private static final int PHONE_NUMBER_LENGTH = 9;
    private final String TAG = "LoginActivity";

    @BindView(R.id.layout_loading)
    LinearLayout llLoading;
    @BindView(R.id.layout_not_network)
    RelativeLayout rlNotNetwork;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.smscode)
    SmsCodeView smsCodeView;
    @BindView(R.id.iv_biometric)
    ImageView ivBiometric;

    private LoginPresenter presenter;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        checkNetworkConnectivity();
        mAuth = FirebaseAuth.getInstance();
        presenter = new LoginPresenter();
        presenter.setView(this);
        initPhoneAuthCallback();
        initTextChangeListener();
    }

    private void initTextChangeListener() {
        etPhoneNumber.addTextChangedListener(
                new TextWatcherAdapter() {
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i1, int i2, int i3) {
                        if (charSequence.length() == PHONE_NUMBER_LENGTH) {
                            etPhoneNumber.setError(null);
                            presenter.addPrefrix(getPhoneNumber());
                        } else {
                            etPhoneNumber.setError(getString(R.string.invalid_phone_number));
                            hideEditTextSmsCode();
                        }
                    }
                });
    }

    private void authProvider(String phoneNumberWithPrefix) {
        PhoneAuthProvider.getInstance()
                .verifyPhoneNumber(phoneNumberWithPrefix,
                        60,
                        TimeUnit.SECONDS,
                        this,
                        mCallbacks);
    }

    private void initPhoneAuthCallback() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                showEditTextSmsCode();
                hideLoading();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    showToast(R.string.text_invalid_credentials);
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    showToast(R.string.text_too_many_requests);
                }
                hideLoading();
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
                showEditTextSmsCode();
                hideLoading();
            }
        };
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private String getPhoneNumber() {
        return etPhoneNumber.getText().toString();
    }

    private void showEditTextSmsCode() {
        smsCodeView.setVisibility(View.VISIBLE);
    }

    private void hideEditTextSmsCode() {
        smsCodeView.setVisibility(View.GONE);
    }

    @Override
    public void phoneNumberEmpty() {
        showToast(R.string.phone_number_must_not_be_empty);
    }

    @Override
    public void requestValidationCode(String phoneNumberWithPrefix) {
        checkNetworkConnectivity();
        showLoading();
        authProvider(phoneNumberWithPrefix);
    }

    public void loginSuccess() {
        goToMainActivity();
    }

    public void loginAuthInvalidCredentials() {
        showToast(R.string.text_invalid_code);
    }

    public void onSubmit(String validationCode) {
        if (!TextUtils.isEmpty(mVerificationId)) {
            checkNetworkConnectivity();
            authWithCredentials(mVerificationId, validationCode);
        }
    }

    private void authWithCredentials(String verificationId, String validationCode) {
        showLoading();
        mAuth.signInWithCredential(getPhoneAuthCredential(verificationId, validationCode))
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        loginSuccess();
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            loginAuthInvalidCredentials();
                        }
                    }
                    hideLoading();
                });
    }

    private PhoneAuthCredential getPhoneAuthCredential(String verificationId, String validationCode) {
        return PhoneAuthProvider.getCredential(verificationId, validationCode);
    }

    private void displayBiometricPrompt() {
        new BiometricManager.BiometricBuilder(LoginActivity.this)
                .setTitle(getString(R.string.biometric_title))
                .setSubtitle(getString(R.string.biometric_subtitle))
                .setDescription(getString(R.string.biometric_description))
                .setNegativeButtonText(getString(R.string.biometric_negative_button_text))
//                .build()
                .authenticate(LoginActivity.this);
    }

    @OnClick(R.id.iv_biometric)
    public void onBiometricClicked(){
        displayBiometricPrompt();
    }

    @Override
    public void showLoading() {
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        llLoading.setVisibility(View.GONE);
    }

    @Override
    public void showNotNetworkConnection() {
        rlNotNetwork.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNotNetworkConnection() {
        rlNotNetwork.setVisibility(View.GONE);
    }

    @Override
    public void onSdkVersionNotSupported() {
        showToast(R.string.biometric_error_sdk_not_supported);
    }

    @Override
    public void onBiometricAuthenticationNotSupported() {
        showToast(R.string.biometric_error_hardware_not_supported);
    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {
        showToast(R.string.biometric_error_fingerprint_not_available);
    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {
        showToast(R.string.biometric_error_permission_not_granted);
    }

    @Override
    public void onBiometricAuthenticationInternalError(String error) {
        showToast(error);
    }

    @Override
    public void onAuthenticationFailed() {
//        showToast(R.string.biometric_failure), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationCancelled() {
        showToast(R.string.biometric_cancelled);
    }

    @Override
    public void onAuthenticationSuccessful() {
        goToMainActivity();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
//        Toast.makeText(getApplicationContext(), helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
//        Toast.makeText(getApplicationContext(), errString, Toast.LENGTH_LONG).show();
    }
}
