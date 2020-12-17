package com.example.onlineshop;

import com.example.onlineshop.data.model.Categories;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.model.ProductPhotos;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class GetProductsItemDeserializer implements JsonDeserializer<List<Product>> {
    @Override
    public List<Product> deserialize(JsonElement json, Type typeOfT,
                                     JsonDeserializationContext context) throws JsonParseException {

        List<Product> productArrayList = new ArrayList<>();
        Categories[] categoriesList;
        ProductPhotos[] productPhotosList;

        JsonArray productArray = json.getAsJsonArray();

        for (int i = 0; i < productArray.size(); i++) {
            JsonObject productObject = productArray.get(i).getAsJsonObject();
            JsonArray categoryArray = productObject.getAsJsonArray("categories");
            JsonArray photoArray = productObject.getAsJsonArray("images");
            categoriesList = new Categories[categoryArray.size()];
            productPhotosList = new ProductPhotos[photoArray.size()];


            for (int j = 0; j < categoryArray.size(); j++) {
                JsonObject categoryObject = categoryArray.get(i).getAsJsonObject();
                int id = categoryObject.get("id").getAsInt();
                String name = categoryObject.get("name").getAsString();
                String slug = categoryObject.get("slug").getAsString();
                Categories categories = new Categories(id, name, slug);
                categoriesList[j] = categories;
            }
            for (int j = 0; j < photoArray.size(); j++) {
                JsonObject photoObject = photoArray.get(i).getAsJsonObject();
                int id = photoObject.get("id").getAsInt();
                String name = photoObject.get("name").getAsString();
                String src = photoObject.get("src").getAsString();

                productPhotosList = new ProductPhotos[photoArray.size()];

                ProductPhotos productPhotos = new ProductPhotos(id, name, src);
                productPhotosList[j] = productPhotos;

            }

            int idProduct = productObject.get("id").getAsInt();
            String nameProduct = productObject.get("name").getAsString();
            String slugProduct = productObject.get("slug").getAsString();
            String descriptionProduct = productObject.get("description").getAsString();
            String shortDescriptionProduct = productObject.get("short_description").getAsString();
            String uriProduct = productObject.get("permalink").getAsString();
            String dateCreatedProduct = productObject.get("date_created").getAsString();
            String dateCreatedGmtProduct = productObject.get("date_created_gmt").getAsString();
            String dateModifiedProduct= productObject.get("date_modified").getAsString();
            String dateModifiedGmtProduct = productObject.get("date_modified_gmt").getAsString();
            long priceProduct = Long.parseLong(productObject.get("price").getAsString());
            boolean onSaleProduct=productObject.get("on_sale").getAsBoolean();
            long regularPriceProduct=Long.parseLong(productObject.get("regular_price").getAsString());
            long salePriceProduct=Long.parseLong(productObject.get("sale_price").getAsString());
            JsonArray array =productObject.get("related_ids").getAsJsonArray();
            int[] relatedProducts = new int[array.size()];
            for (int j = 0; j <array.size() ; j++) {
                relatedProducts[j]=array.get(i).getAsInt();
            }

            int rateProduct = productObject.get("rating_count").getAsInt();

            Product product = new Product(idProduct,nameProduct,slugProduct,descriptionProduct,
                    shortDescriptionProduct,uriProduct,dateCreatedProduct,dateCreatedGmtProduct,
                    dateModifiedProduct,dateModifiedGmtProduct,priceProduct,onSaleProduct,
                    regularPriceProduct,salePriceProduct,relatedProducts,rateProduct,
                    productPhotosList,categoriesList);


            productArrayList.add(product);
        }


        return null;
    }



}
