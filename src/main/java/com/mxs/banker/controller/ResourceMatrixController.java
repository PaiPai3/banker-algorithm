package com.mxs.banker.controller;

import com.mxs.banker.model.ResourceMatrix;
import com.mxs.banker.service.ResourceMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/resource-matrix")
@CrossOrigin(origins = "http://localhost:5173")
public class ResourceMatrixController {

    @Autowired
    private ResourceMatrixService resourceMatrixService;

    @GetMapping("/get-by-time")
    public ResourceMatrix getResourceMatrix(Date date) {
        return resourceMatrixService.getResourceMatrix(date).get(0); //依照时间获取资源矩阵
    }

    @GetMapping("/random-generate")
    public ResourceMatrix randomGenerateResourceMatrix(@RequestParam(defaultValue = "5") Integer n, @RequestParam(defaultValue = "3") Integer m) {
        return resourceMatrixService.randomGenerateResourceMatrix(n,m);//生成随机资源矩阵
    }

    @GetMapping("/dispatch")
    public List<List<Integer>> dispatch(ResourceMatrix resourceMatrix) {
        return resourceMatrixService.dispatch(resourceMatrix);
    }

    @PostMapping("/save")
    public ResourceMatrix saveResourceMatrix(@RequestBody ResourceMatrix resourceMatrix) {
        return resourceMatrixService.saveResourceMatrix(resourceMatrix);
    }

}
