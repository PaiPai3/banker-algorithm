package com.mxs.banker.service;

import com.mxs.banker.model.ResourceMatrix;
import com.mxs.banker.repository.ResourceMatrixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ResourceMatrixService {
    @Autowired
    private ResourceMatrixRepository resourceMatrixRepository;

    public ResourceMatrix randomGenerateResourceMatrix(Integer n, Integer m) { // TODO 改为随机
        ResourceMatrix resourceMatrix = new ResourceMatrix(n,m);
        resourceMatrix.setId(resourceMatrixRepository.count() + 1L);
        resourceMatrix.setN(5);
        resourceMatrix.setM(3);
        resourceMatrix.setAvailable("3,3,2");
        resourceMatrix.setMax("7,5,3;3,2,2;9,0,2;2,2,2;4,3,3");
        resourceMatrix.setAllocation("0,1,0;2,0,0;3,0,2;2,1,1;0,0,2");
        resourceMatrix.setNeed("7,4,3;1,2,2;6,0,0;0,1,1;4,3,1");
        resourceMatrix.setTimeStamp(new Date());
        resourceMatrixRepository.save(resourceMatrix);
        return resourceMatrix;
    }

    public List<ResourceMatrix> getResourceMatrix(Date date) {
        return resourceMatrixRepository.findByTimeStamp(date);
    }

    public ResourceMatrix saveResourceMatrix(ResourceMatrix resourceMatrix) {
        return resourceMatrixRepository.save(resourceMatrix);
    }

    public List<List<Integer>> dispatch(ResourceMatrix resourceMatrix) {//调度算法
        //获取进程数和资源种类数
        int n = resourceMatrix.getN();
        int m = resourceMatrix.getM();

        //解析字符串，获得矩阵
        int[] available = Arrays.stream(resourceMatrix.getAvailable().split(",")).mapToInt(Integer::parseInt).toArray();
        int[][] max = Arrays.stream(resourceMatrix.getMax().split(";")).map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
        int[][] allocation = Arrays.stream(resourceMatrix.getAllocation().split(";")).map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
        int[][] need = Arrays.stream(resourceMatrix.getNeed().split(";")).map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);

        //计算所有安全序列
        List<List<Integer>> result = calculateAllSafeSequence(n, m, available, max, allocation, need);

        return result;
    }

    private List<List<Integer>> calculateAllSafeSequence(int n, int m, int[] available, int[][] max, int[][] allocation, int[][] need) {
        //记录访问状态
        boolean[] visited = new boolean[n];
        //结果
        List<List<Integer>> result = new ArrayList<>();
        //路径
        List<Integer> path = new ArrayList<>();
        //深度优先搜索
        dfs(n, m, available, max, allocation, need, result, visited,path, 0);

        return result;
    }

    private void dfs(int n,int m, int[] available, int[][] max, int[][] allocation, int[][] need, List<List<Integer>> result, boolean[] visited, List<Integer> path, int depth) {
        //找到一条路径
        if (depth == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            //已经访问过
            if (visited[i]) {
                continue;
            }
            //判断资源是否足够
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (need[i][j] > available[j]) {
                    flag = false;
                    break;
                }
            }
            //资源足够
            if (flag) {
                //可分配，于是释放资源
                for (int j = 0; j < m; j++) {
                    available[j] += allocation[i][j];
                }
                //记录访问状态
                visited[i] = true;
                //记录路径
                path.add(i);
                //递归
                dfs(n, m, available, max, allocation, need, result, visited, path, depth + 1);
                //回溯
                path.remove(path.size() - 1);
                //撤销访问状态
                visited[i] = false;
                //撤销释放资源
                for (int j = 0; j < m; j++) {
                    available[j] -= allocation[i][j];
                }
            }
        }
    }

    private boolean checkSafety(int n, int m, int[] available, int[][] max, int[][] allocation, int[][] need) { //安全性检查
        int[] work = available.clone();

        boolean[] finish = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (finish[i]) {
                continue;
            }
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (need[i][j] > work[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for (int j = 0; j < m; j++) {
                    work[j] += allocation[i][j];
                }
                finish[i] = true;
                i = -1; //从头开始
            }
        }

        return false;
    }
}
