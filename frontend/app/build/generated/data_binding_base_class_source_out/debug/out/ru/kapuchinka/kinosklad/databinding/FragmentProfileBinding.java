// Generated by view binder compiler. Do not edit!
package ru.kapuchinka.kinosklad.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ru.kapuchinka.kinosklad.R;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonEdit;

  @NonNull
  public final Button buttonLogout;

  @NonNull
  public final ConstraintLayout frameLayout;

  @NonNull
  public final TextView textProfile;

  private FragmentProfileBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonEdit,
      @NonNull Button buttonLogout, @NonNull ConstraintLayout frameLayout,
      @NonNull TextView textProfile) {
    this.rootView = rootView;
    this.buttonEdit = buttonEdit;
    this.buttonLogout = buttonLogout;
    this.frameLayout = frameLayout;
    this.textProfile = textProfile;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_edit;
      Button buttonEdit = ViewBindings.findChildViewById(rootView, id);
      if (buttonEdit == null) {
        break missingId;
      }

      id = R.id.button_logout;
      Button buttonLogout = ViewBindings.findChildViewById(rootView, id);
      if (buttonLogout == null) {
        break missingId;
      }

      ConstraintLayout frameLayout = (ConstraintLayout) rootView;

      id = R.id.text_profile;
      TextView textProfile = ViewBindings.findChildViewById(rootView, id);
      if (textProfile == null) {
        break missingId;
      }

      return new FragmentProfileBinding((ConstraintLayout) rootView, buttonEdit, buttonLogout,
          frameLayout, textProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}