package com.onlinebookstore.web.controller;

import com.onlinebookstore.common.model.response.ResponseResult;
import com.onlinebookstore.common.model.vo.ShoppingCartVO;
import com.onlinebookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description Shopping Cart Controller
 * @author henryzheng
 * @date  2024/09/26
 */
@Tag(name = "Shopping Cart Module API")
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Operation(
            summary = "Add goods to shopping cart interface",
            description = "Users can use this interface to add their preferred items to their shopping cart. " +
                    " If the selected item is not already in the cart, it will be added directly. " +
                    " However, if the item is already present in the cart, the quantity will be updated by adding the selected amount to the existing quantity. " +
                    " This approach enhances the user experience by simplifying the shopping process. This logic is commonly used on major e-commerce platforms.")
    @Parameters({
            @Parameter(name = "goodsId", description = "The ID of the goods added to the cart", in = ParameterIn.QUERY, style = ParameterStyle.FORM, required = true),
            @Parameter(name = "goodsQuantity", description = "The number of goods added to the cart", in = ParameterIn.QUERY, style = ParameterStyle.FORM, required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Added goods to shopping cart successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameter"),
    })
    @PostMapping("/add")
    public ResponseResult<String> add(
            @RequestParam(name = "goodsId", required = true, defaultValue = "") Integer goodsId,
            @RequestParam(name = "goodsQuantity", required = true, defaultValue = "") Double goodsQuantity
    ) {
        try {
            if (goodsId == null || goodsId <= 0) {
                return ResponseResult.fail("Goods ID must not be empty");
            }
            if (goodsQuantity == null || goodsQuantity <= 0) {
                return ResponseResult.fail("Goods Quantity must not be empty");
            }

            ShoppingCartVO shoppingCart = new ShoppingCartVO();
            shoppingCart.setGoodsId(goodsId);
            shoppingCart.setGoodsQuantity(goodsQuantity);
            shoppingCartService.add(shoppingCart);

            return ResponseResult.ok();
        } catch (Exception e){
            return ResponseResult.fail(e.getMessage());
        }
    }

    @Operation(
            summary = "Query shopping cart interface",
            description = "Users can use this interface to query information about all goods that have been added to the shopping cart.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Query shopping cart successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameter"),
    })
    @GetMapping("/find")
    public ResponseResult<List<ShoppingCartVO>> findAll() {
        try {
            return ResponseResult.ok(shoppingCartService.findAll());
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

}
