// Generated by view binder compiler. Do not edit!
package ru.kapuchinka.kinosklad.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ru.kapuchinka.kinosklad.R;

public final class FragmentRegistrationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonReg;

  @NonNull
  public final ConstraintLayout frameLayout8;

  @NonNull
  public final EditText regEmail;

  @NonNull
  public final EditText regNick;

  @NonNull
  public final EditText regPassword;

  @NonNull
  public final EditText regPasswordRepeat;

  private FragmentRegistrationBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonReg,
      @NonNull ConstraintLayout frameLayout8, @NonNull EditText regEmail, @NonNull EditText regNick,
      @NonNull EditText regPassword, @NonNull EditText regPasswordRepeat) {
    this.rootView = rootView;
    this.buttonReg = buttonReg;
    this.frameLayout8 = frameLayout8;
    this.regEmail = regEmail;
    this.regNick = regNick;
    this.regPassword = regPassword;
    this.regPasswordRepeat = regPasswordRepeat;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRegistrationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRegistrationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_registration, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRegistrationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_reg;
      Button buttonReg = ViewBindings.findChildViewById(rootView, id);
      if (buttonReg == null) {
        break missingId;
      }

      ConstraintLayout frameLayout8 = (ConstraintLayout) rootView;

      id = R.id.reg_email;
      EditText regEmail = ViewBindings.findChildViewById(rootView, id);
      if (regEmail == null) {
        break missingId;
      }

      id = R.id.reg_nick;
      EditText regNick = ViewBindings.findChildViewById(rootView, id);
      if (regNick == null) {
        break missingId;
      }

      id = R.id.reg_password;
      EditText regPassword = ViewBindings.findChildViewById(rootView, id);
      if (regPassword == null) {
        break missingId;
      }

      id = R.id.reg_password_repeat;
      EditText regPasswordRepeat = ViewBindings.findChildViewById(rootView, id);
      if (regPasswordRepeat == null) {
        break missingId;
      }

      return new FragmentRegistrationBinding((ConstraintLayout) rootView, buttonReg, frameLayout8,
          regEmail, regNick, regPassword, regPasswordRepeat);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}