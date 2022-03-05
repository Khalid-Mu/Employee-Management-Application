package com.example.project457;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter <MainModel,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull MainModel model) {

    holder.Id.setText(model.getId());
    holder.Name.setText(model.getName());
    holder.Age.setText(model.getAge());
    holder.Experience.setText(model.getExperience());
    holder.Salary.setText(model.getSalary());


    holder.btnEdit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                    .setContentHolder(new ViewHolder(R.layout.update_popup))
                    .setExpanded(true, 1650).create();
            //dialogPlus.show();
        View view = dialogPlus.getHolderView();

            EditText Id = view.findViewById(R.id.txtId);
            EditText Name = view.findViewById(R.id.txtName);
            EditText Age = view.findViewById(R.id.txtAge);
            EditText Experience = view.findViewById(R.id.txtExperience);
            EditText Salary = view.findViewById(R.id.txtSalary);

            Button btnUpdate = view.findViewById(R.id.btnUpdate);
            Id.setText(model.getId());
            Name.setText(model.getName());
            Age.setText(model.getAge());
            Experience.setText(model.getExperience());
            Salary.setText(model.getSalary());

            dialogPlus.show();
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("Id",Id.getText().toString());
                    map.put("Name",Name.getText().toString());
                    map.put("Age",Age.getText().toString());
                    map.put("Experience",Experience.getText().toString());
                    map.put("Salary",Salary.getText().toString());

                    FirebaseDatabase.getInstance().getReference().child("Employees")
                            .child(getRef(position).getKey()).updateChildren(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(holder.Id.getContext(), "Data Has been Updated", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(holder.Id.getContext(), "Data Has Not Been Updated", Toast.LENGTH_SHORT).show();
dialogPlus.dismiss();
                                }
                            });
                }
            });

        }
    });
holder.btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(holder.Id.getContext());
        builder.setTitle("Delete Employee ?");
                builder.setMessage("Are you sure");
                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
FirebaseDatabase.getInstance().getReference().child("Employees").child(getRef(position).getKey()).removeValue();
                            }
                        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(holder.Id.getContext(), "Employee Has been Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        builder.show();
    }
});
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView Id,Name,Age,Experience,Salary;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            Id=(TextView)itemView.findViewById(R.id.Idtext);
            Name=(TextView)itemView.findViewById(R.id.nametext);
            Age=(TextView)itemView.findViewById(R.id.Agetext);
            Experience=(TextView)itemView.findViewById(R.id.Exptext);
            Salary=(TextView)itemView.findViewById(R.id.Salarytext);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }
}
