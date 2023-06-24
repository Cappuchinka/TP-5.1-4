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

public final class FragmentLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonLogin;

  @NonNull
  public final ConstraintLayout frameLayout7;

  @NonNull
  public final EditText regEmail;

  @NonNull
  public final EditText regPassword;

  private FragmentLoginBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonLogin,
      @NonNull ConstraintLayout frameLayout7, @NonNull EditText regEmail,
      @NonNull EditText regPassword) {
    this.rootView = rootView;
    this.buttonLogin = buttonLogin;
    this.frameLayout7 = frameLayout7;
    this.regEmail = regEmail;
    this.regPassword = regPassword;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_login;
      Button buttonLogin = ViewBindings.findChildViewById(rootView, id);
      if (buttonLogin == null) {
        break missingId;
      }

      ConstraintLayout frameLayout7 = (ConstraintLayout) rootView;

      id = R.id.reg_email;
      EditText regEmail = ViewBindings.findChildViewById(rootView, id);
      if (regEmail == null) {
        break missingId;
      }

      id = R.id.reg_password;
      EditText regPassword = ViewBindings.findChildViewById(rootView, id);
      if (regPassword == null) {
        break missingId;
      }

      return new FragmentLoginBinding((ConstraintLayout) rootView, buttonLogin, frameLayout7,
          regEmail, regPassword);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}