// package id.cranium.erp.purchasing.dto.order;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import
// id.cranium.erp.purchasing.validator.constraint.PurchasingRequestIsExists;
// import jakarta.validation.Valid;
// import jakarta.validation.constraints.Max;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
// import lombok.Builder;
// import lombok.Data;
// import lombok.extern.jackson.Jacksonized;

// import java.time.LocalDate;
// import java.util.List;

// @Data
// @Builder
// @Jacksonized
// public class OrderCreateDto {
// @PurchasingRequestIsExists
// private Long purchaseRequestId;
// @NotNull(message = "{purchasing.branchId.notnull}")
// private Long branchId;
// @NotNull(message = "{purchasing.supplierId.notnull}")
// private Long supplierId;
// @NotNull(message = "{purchasing.supplierAddress.notnull}")
// @NotEmpty(message = "{purchasing.supplierAddress.notempty}")
// private String supplierAddress;
// @NotNull(message = "{purchasing.paymentMethod.notnull}")
// @Max(value = 2, message = "{purchasing.paymentMethod.max}")
// @Min(value = 1, message = "{purchasing.paymentMethod.min}")
// private Integer paymentMethod;
// private Integer termOfPayment;
// @NotNull(message = "{purchasing.supplierDocumentReferenceNo.notnull}")
// @NotEmpty(message = "{purchasing.supplierDocumentReferenceNo.notempty}")
// private String supplierDocumentReferenceNo;
// private String supplierInvoiceDocumentReferenceNo;
// @NotNull(message = "{purchasing.additionalPph.notnull}")
// @Max(value = 90, message = "{purchasing.additionalPph.max}")
// @Min(value = 0, message = "{purchasing.additionalPph.min}")
// private Double additionalPphPercentage = 0D;
// @NotNull(message = "{purchasing.transactionDate.notnull}")
// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
// private LocalDate transactionDate;
// @NotNull(message = "{purchasing.requestDeliveryDate.notnull}")
// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
// private LocalDate requestDeliveryDate;
// @NotNull(message = "{purchasing.ppn.notnull}")
// private Boolean ppn;
// @Size(max = 255, message = "{purchasing.remarksHeader.max}")
// private String remarks;
// @NotNull(message = "{purchasing.detail.notnull}")
// @NotEmpty(message = "{purchasing.detail.notempty}")
// private List<@Valid OrderDetailCreateDto> orderDetailCreateDtos;
// private Float poPoirTotalCreatedQuantity;

// }