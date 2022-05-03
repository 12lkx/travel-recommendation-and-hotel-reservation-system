package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.SysOperLogService;
import se.xmut.trahrs.domain.model.SysOperLog;


/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/sysOperLog")
public class SysOperLogController {

    final Logger logger = LoggerFactory.getLogger(SysOperLogController.class);
    @Autowired
    private SysOperLogService sysOperLogService;

    @WebLog(description = "添加操作日志记录")
    @PostMapping
    public ApiResponse save(@RequestBody SysOperLog sysOperLog) {
        return ApiResponse.ok(sysOperLogService.saveOrUpdate(sysOperLog));
    }

    @WebLog(description = "用id删除操作日志记录")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysOperLogService.removeById(id));
    }

    @WebLog(description = "查询全部操作日志记录")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysOperLogService.list());
    }

    @WebLog(description = "用id查找操作日志记录")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysOperLogService.getById(id));
    }

    @WebLog(description = "分页操作日志记录")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysOperLogService.page(new Page<>(pageNum, pageSize)));
    }

}

