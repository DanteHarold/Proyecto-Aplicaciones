package com.appmovil.proyecto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link noticiafragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class noticiafragmento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public noticiafragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment noticiafragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static noticiafragmento newInstance(String param1, String param2) {
        noticiafragmento fragment = new noticiafragmento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Button btnSubir;
    private StorageReference miStorage;
    private static final int GALLERY_INTENT = 1;
    private ProgressDialog porProgressDialog;

    List<CarouselItem> list = new ArrayList<>();
    ImageCarousel carousel;
    private TextView miTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View root = inflater.inflate(R.layout.fragment_noticiafragmento, container, false);
        // Inflate the layout for this fragment
        carousel = root.findViewById(R.id.carousel);
        list.add(new CarouselItem("https://scontent.flim12-1.fna.fbcdn.net/v/t1.18169-9/15541346_1836169543337141_3927238408173640479_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=8bfeb9&_nc_eui2=AeG_oO-KRq0OO9ifI4_XxjR5MQEC7BhBIEIxAQLsGEEgQvNB9sBTjVuJdOIlwpw-_CDzfp2v-omzRcfYPmQMXk6u&_nc_ohc=Mil2x5XQy-oAX8Jad9y&_nc_ht=scontent.flim12-1.fna&oh=00_AT8agQVZBqQdg3WGbvj82WyylI35awuIrF2W4l0imc72oA&oe=62EA1A53","IMG!"));
        list.add(new CarouselItem("https://scontent.flim12-1.fna.fbcdn.net/v/t1.18169-9/15590321_1836165040004258_6748716249556425299_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=8bfeb9&_nc_eui2=AeEbkiN2t6BpZAucJ9YNRIf1kgmCPe0U19eSCYI97RTX16VqsgsSjVTW39rjTPqDiNLXuFofYtqshIxwcknNnB1B&_nc_ohc=No7rC3KoY3wAX9iXWVw&_nc_oc=AQn4OYsLqZh47aly9jfNMVp67ilBJ9VGEKztXdMbXYulx_2eg3Zd4Q3LqDLdqLwXGJg&_nc_ht=scontent.flim12-1.fna&oh=00_AT8oVmFGKtgEpHp38muPujEtBWf09wTMZifUEf3HN3Gtdw&oe=62E87EAF","IMG 2"));
        list.add(new CarouselItem("https://scontent.flim12-1.fna.fbcdn.net/v/t1.18169-9/15136004_1821600354794060_7962728859612181919_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=cdbe9c&_nc_eui2=AeEQeLL9-VQnjjIT7CMIlZvIDsO8DGmQwXQOw7wMaZDBdCZyOv8wW4x-MQiQBwx81eT_CL3AcQh73JB33Zq3IKOJ&_nc_ohc=3cMw6gg9_OgAX9-Xm9B&tn=vJOr8Ee5OKer_3Qq&_nc_ht=scontent.flim12-1.fna&oh=00_AT_ZVDlCNQFFqpkobdnmrAtaOpmD5zdMgjNBfwLZh9Q66w&oe=62E9F5B8","IMG 2"));
        list.add(new CarouselItem("https://scontent.flim12-1.fna.fbcdn.net/v/t1.18169-9/15541346_1836169543337141_3927238408173640479_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=8bfeb9&_nc_eui2=AeG_oO-KRq0OO9ifI4_XxjR5MQEC7BhBIEIxAQLsGEEgQvNB9sBTjVuJdOIlwpw-_CDzfp2v-omzRcfYPmQMXk6u&_nc_ohc=Mil2x5XQy-oAX8Jad9y&_nc_ht=scontent.flim12-1.fna&oh=00_AT8agQVZBqQdg3WGbvj82WyylI35awuIrF2W4l0imc72oA&oe=62EA1A53","IMG!"));
        list.add(new CarouselItem("https://scontent.flim12-1.fna.fbcdn.net/v/t1.18169-9/15590321_1836165040004258_6748716249556425299_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=8bfeb9&_nc_eui2=AeEbkiN2t6BpZAucJ9YNRIf1kgmCPe0U19eSCYI97RTX16VqsgsSjVTW39rjTPqDiNLXuFofYtqshIxwcknNnB1B&_nc_ohc=No7rC3KoY3wAX9iXWVw&_nc_oc=AQn4OYsLqZh47aly9jfNMVp67ilBJ9VGEKztXdMbXYulx_2eg3Zd4Q3LqDLdqLwXGJg&_nc_ht=scontent.flim12-1.fna&oh=00_AT8oVmFGKtgEpHp38muPujEtBWf09wTMZifUEf3HN3Gtdw&oe=62E87EAF","IMG 2"));
        list.add(new CarouselItem("https://scontent.flim12-1.fna.fbcdn.net/v/t1.18169-9/15136004_1821600354794060_7962728859612181919_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=cdbe9c&_nc_eui2=AeEQeLL9-VQnjjIT7CMIlZvIDsO8DGmQwXQOw7wMaZDBdCZyOv8wW4x-MQiQBwx81eT_CL3AcQh73JB33Zq3IKOJ&_nc_ohc=3cMw6gg9_OgAX9-Xm9B&tn=vJOr8Ee5OKer_3Qq&_nc_ht=scontent.flim12-1.fna&oh=00_AT_ZVDlCNQFFqpkobdnmrAtaOpmD5zdMgjNBfwLZh9Q66w&oe=62E9F5B8","IMG 2"));

        carousel.setCarouselListener(new CarouselListener() {
            @Override
            public void onLongClick(int i, CarouselItem carouselItem) {

            }

            @Override
            public ViewBinding onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void onBindViewHolder(ViewBinding viewBinding, CarouselItem carouselItem, int i) {

            }

            @Nullable
            @Override
            public void onClick(int position, @NotNull CarouselItem carouselItem) {
                Toast.makeText(getContext(),"IMG : "+carouselItem.getCaption(),Toast.LENGTH_LONG).show();
            }
        });
        carousel.addData(list);


        miTextView = (TextView) root.findViewById(R.id.txtMarquee);
        miTextView.setSelected(true);
        porProgressDialog = new ProgressDialog(getContext());
        // IMGES
        miStorage = FirebaseStorage.getInstance().getReference();


        btnSubir = root .findViewById(R.id.btnSubir);

        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });

        return  root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT){

            porProgressDialog.setTitle("SUBIENDO...");
            porProgressDialog.setMessage("Subiendo Foto a FIREBASE");
            porProgressDialog.setCancelable(false);
            porProgressDialog.show();

            Uri url = data.getData();

            StorageReference filePath = miStorage.child("fotos").child(url.getLastPathSegment());
            filePath.putFile(url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    porProgressDialog.dismiss();
                    Task<Uri> descarga = taskSnapshot.getStorage().getDownloadUrl();

                    Log.d("=>>", String.valueOf(descarga));
                    descarga.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.d("=>>", uri.toString());

                            list.add(new CarouselItem(uri.toString(),"IMG 5"));


                            carousel.addData(list);
                        }
                    });

                    Toast.makeText(getContext(),"Archivo Cargado", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}