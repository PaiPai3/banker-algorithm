package com.mxs.banker.controller;

import com.mxs.banker.model.ResourceMatrix;
import com.mxs.banker.service.ResourceMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResourceMatrix randomGenerateResourceMatrix() {
        return resourceMatrixService.randomGenerateResourceMatrix();//生成随机资源矩阵
    }

    @GetMapping("/dispatch")
    public List<List<Integer>> dispatch(ResourceMatrix resourceMatrix) {
        return resourceMatrixService.dispatch(resourceMatrix);
    }


}
