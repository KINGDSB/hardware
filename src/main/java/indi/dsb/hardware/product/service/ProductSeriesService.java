package indi.dsb.hardware.product.service;

import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.product.entity.ProductSeries;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface ProductSeriesService extends AbstractService<ProductSeries, Long> {

    List<ProductSeries> selectAll();
}
