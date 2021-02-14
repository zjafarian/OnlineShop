package com.example.onlineshop.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListReviewAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.models.Review;
import com.example.onlineshop.databinding.FragmentProductDetailBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.example.onlineshop.viewmodel.ProductDetailViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailFragment extends Fragment {
    public static final String ARGS_ID_PRODUCT_REVIEW = "idProductReview";
    public static final String ARGS_PRODUCT_ID = "productId";
    public static final String ARGS_WHICH_PAGE_SHOPPING = "whichPageShopping";
    private int mProductId;
    private ProductDetailViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;
    private SliderAdapter mSliderAdapter;
    private Products mProduct;
    private boolean mShowDescription = false;


    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(int productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_PRODUCT_ID, productId);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null)
            mProductId = getArguments().getInt(ARGS_PRODUCT_ID);

        mViewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);
        mViewModel.findProduct(mProductId);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_product_detail,
                container,
                false);



        listener();

        return mBinding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLiveDataObserver();
    }

    private void listener() {
        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackNavigation();
            }
        });

        mBinding.btnProductAddToShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.addProductToShoppingCart(mProduct);
            }
        });

        mBinding.imgBtnShopCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShoppingCartPage();

            }
        });

        mBinding.productCommentsUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToReviewPage();


            }
        });

        mBinding.productOtherDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShowDescription = !mShowDescription;

                if(mShowDescription)
                    mBinding.productDescription.setVisibility(View.VISIBLE);
                else  mBinding.productDescription.setVisibility(View.GONE);

            }
        });

        mBinding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareReportIntent(mViewModel.getProductLiveData().getValue());
            }
        });

    }

    private void setLiveDataObserver() {
        mViewModel.getProductLiveData().observe(getViewLifecycleOwner(), new Observer<Products>() {
            @Override
            public void onChanged(Products products) {
                String des = parseHtml(products.getDescription());
                String desShort = parseHtml(products.getShortDescription());
                products.setDescription(des);
                products.setShortDescription(desShort);
                mProduct = products;
                mBinding.setProduct(products);
                initSlider();
            }
        });

        mViewModel.getProductsShoppingCartLiveData().observe(getViewLifecycleOwner(), new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                String count = String.valueOf(products.size());
                mBinding.textViewNumberProductsShopping.setText(count);
            }
        });


    }

    public void initSlider() {
        List<String> imagesUri = mViewModel.getImagesUriProduct();
        mSliderAdapter = new SliderAdapter(imagesUri);

        if (imagesUri.size() != 0 && imagesUri != null) {
            mBinding.productImagesSlider.setSliderAdapter(mSliderAdapter);
            mBinding.productImagesSlider.startAutoCycle();
            mBinding.productImagesSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
            mBinding.productImagesSlider.setSliderTransformAnimation
                    (SliderAnimations.SIMPLETRANSFORMATION);
        }

    }

    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }

    private void goToShoppingCartPage() {
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_WHICH_PAGE_SHOPPING, "productPage");

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.shopping_cart_fragment_des, bundle);
    }

    private void goToReviewPage() {
        Bundle bundle = new Bundle();
        bundle.putInt( ARGS_ID_PRODUCT_REVIEW,mViewModel.getProductLiveData().getValue().getId());


        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.review_fragment_des,bundle);
    }

    private void shareReportIntent(Products product) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, product.getName());
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, product.getPermalink());
        sendIntent.setType("text/plain");

        Intent shareIntent =
                Intent.createChooser(sendIntent, getString(R.string.send_report));

        //we prevent app from crash if the intent has no destination.
        if (sendIntent.resolveActivity(getActivity().getPackageManager()) != null)
            startActivity(shareIntent);
    }


    public String parseHtml(String des) {
        if (des == null)
            return des;
        Document document = Jsoup.parse(des);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }


}