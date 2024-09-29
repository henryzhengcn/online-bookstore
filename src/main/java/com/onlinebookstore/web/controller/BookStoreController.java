package com.onlinebookstore.web.controller;

import com.onlinebookstore.common.model.response.ResponseResult;
import com.onlinebookstore.common.model.vo.BookVO;
import com.onlinebookstore.service.BookStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description Book Management Controller
 * @author henryzheng
 * @date  2024/09/26
 */
@Tag(name = "Book Management Module API")
@RestController
@RequestMapping("/book")
public class BookStoreController {

    @Autowired
    private BookStoreService bookStoreService;

    @Operation(summary = "Add book interface", description="Users can add new books through this interface.")
    @Parameters({
            @Parameter(name = "title", description = "Book Title", in = ParameterIn.QUERY, style = ParameterStyle.FORM, required = true),
            @Parameter(name = "author", description = "Author Name", in = ParameterIn.QUERY, style = ParameterStyle.FORM, required = true),
            @Parameter(name = "price", description = "Book Prices", in = ParameterIn.QUERY, style = ParameterStyle.FORM, required = true),
            @Parameter(name = "category", description = "Book Category", in = ParameterIn.QUERY, style = ParameterStyle.FORM, required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Added book successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid paramete"),
    })
    @PostMapping("/add")
    public ResponseResult<String> add(
            @RequestParam(name = "title", required = true, defaultValue = "") String title,
            @RequestParam(name = "author", required = true, defaultValue = "") String author,
            @RequestParam(name = "price", required = true, defaultValue = "") Double price,
            @RequestParam(name = "category", required = true, defaultValue = "") String category
    ) {
        try {
            if (!StringUtils.hasText(title)) {
                return ResponseResult.fail("Title must not be empty");
            }
            if (!StringUtils.hasText(author)) {
                return ResponseResult.fail("Author must not be empty");
            }
            if (price == null || price <= 0) {
                return ResponseResult.fail("Price must be greater than 0");
            }
            if (!StringUtils.hasText(category)) {
                return ResponseResult.fail("Category must not be category");
            }

            BookVO book = new BookVO();
            book.setTitle(title);
            book.setAuthor(author);
            book.setPrice(price);
            book.setCategory(category);
            bookStoreService.add(book);

            return ResponseResult.ok();
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @Operation(summary = "Query book interface", description="Users can query all added books through this interface")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Query book successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid paramete"),
    })
    @GetMapping("/find")
    public ResponseResult<List<BookVO>> findAll() {
        try {
            return ResponseResult.ok(bookStoreService.findAll());
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

}
