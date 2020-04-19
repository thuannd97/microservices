package com.thuannd.api.api.composite.product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "REST API for composite product information.")
public interface ProductCompositeService {

    @ApiOperation(
        value = "create composite product description"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @GetMapping("/product-composite")
    void createCompositeProduct(@RequestBody ProductAggregate productAggregate);

    @ApiOperation(
        value = "delete composite product description"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @DeleteMapping("/composite-product/{productId}")
    void deleteCompositeProduct(@PathVariable Integer productId);

    @ApiOperation(
        value = "delete composite product description"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 422, message = "Unprocessable entity"),
        @ApiResponse(code = 400, message = "Not found")
    })
    @GetMapping(
        value = "/product-composite/{productId}",
        produces = "application/json"
    )
    ProductAggregate getProduct(@PathVariable int productId);

}