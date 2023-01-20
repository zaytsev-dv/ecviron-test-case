package com.ecviron.processor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PostEventRequest
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEventRequest {
  @JsonProperty("seller")
  private String seller;

  @JsonProperty("customer")
  private String customer;

  @JsonProperty("productsList")
  private List<ProductDTO> productsList = null;

  public PostEventRequest seller(String seller) {
    this.seller = seller;
    return this;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public PostEventRequest customer(String customer) {
    this.customer = customer;
    return this;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public PostEventRequest productsList(List<ProductDTO> productsList) {
    this.productsList = productsList;
    return this;
  }

  public PostEventRequest addProductsListItem(ProductDTO productsListItem) {
    if (this.productsList == null) {
      this.productsList = new ArrayList<>();
    }
    this.productsList.add(productsListItem);
    return this;
  }

  public List<ProductDTO> getProductsList() {
    return productsList;
  }

  public void setProductsList(List<ProductDTO> productsList) {
    this.productsList = productsList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostEventRequest postEventRequest = (PostEventRequest) o;
    return Objects.equals(this.seller, postEventRequest.seller) &&
        Objects.equals(this.customer, postEventRequest.customer) &&
        Objects.equals(this.productsList, postEventRequest.productsList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seller, customer, productsList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostEventRequest {\n");
    
    sb.append("    seller: ").append(toIndentedString(seller)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    productsList: ").append(toIndentedString(productsList)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

