package com.onlinebookstore.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.onlinebookstore.common.model.response.ResponseResult;
import com.onlinebookstore.service.TradingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description Trading Controller
 * @author henryzheng
 * @date  2024/09/26
 */
@Tag(name = "Trading Module API")
@RestController
@RequestMapping(value = "/trading")
@ResponseBody
public class TradingController {

    @Autowired
    private TradingService tradingService;

    @Operation(
            summary = "Shopping cart checkout interface",
            description = "Users can use this interface to checkout specific items added to their shopping cart. " +
                    "They can select one or multiple items for checkout simultaneously. " +
                    " The checkout feature calculates the total number of items purchased and the total amount payable. " +
                    " It also provides a detailed list of checked-out items, along with any invalid cart additions.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The record ID of adding goods to the shopping cart. You can select one or more items.",
            required = true,
            content = @Content(examples = @ExampleObject(value = "[1,2,3]"), schema = @Schema(type = "List", example = "[1,2,3]"))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Checkout successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameter"),
    })
    @PostMapping(value = "/checkout")
    public ResponseResult<JSONObject> checkout(@RequestBody String requestObj) {
        try {
            List<Integer> shoppingCartIds = JSON.parseObject(requestObj, List.class);
            if (CollectionUtils.isEmpty(shoppingCartIds)) {
                return ResponseResult.fail("Invalid parameter");
            }

            return ResponseResult.ok(tradingService.checkout(shoppingCartIds));
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

}
