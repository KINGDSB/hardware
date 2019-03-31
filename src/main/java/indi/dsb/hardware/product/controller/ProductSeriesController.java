package indi.dsb.hardware.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import indi.dsb.hardware.common.Log;
import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.ResponseCode;
import indi.dsb.hardware.common.abstracts.AbstractController;
import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.common.utils.FileUtils;
import indi.dsb.hardware.common.utils.Response;
import indi.dsb.hardware.product.entity.ProductSeries;
import indi.dsb.hardware.product.service.ProductSeriesService;
import indi.dsb.hardware.sys.entity.SysUser;
import indi.dsb.hardware.sys.service.SysResourceService;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping(value = "productSeries")
public class ProductSeriesController extends AbstractController<ProductSeries, Long> {
	@Autowired
	private ProductSeriesService productSeriesService ;
	@Autowired
	private SysResourceService sysResourceService;

	@Override
	public AbstractService<ProductSeries, Long> getService() {
		return productSeriesService;
	}

	@Override
	public SysResourceService getSysResourceService() {
		return sysResourceService;
	}

	@RequestMapping(value = "/list")
	@Log(module = "产品系列管理", method = "产品系列列表")
	public ModelAndView find(HttpServletRequest request, HttpServletResponse response) {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int length = ServletRequestUtils.getIntParameter(request, "length", 10);
		int draw = ServletRequestUtils.getIntParameter(request, "draw", 1);
		String nameCn = ServletRequestUtils.getStringParameter(request, "nameCn", null);
		String nameEn = ServletRequestUtils.getStringParameter(request, "nameEn", null);

		RowBounds rowBounds = new RowBounds(start, length);

		Example example = new Example(ProductSeries.class);
		if (!StringUtils.isBlank(nameCn))
			example.createCriteria().andLike("nameCn", "%"+nameCn+"%");
		if (!StringUtils.isBlank(nameEn))
			example.createCriteria().andLike("nameEn", "%"+nameEn+"%");

		example.createCriteria().andEqualTo("isDeleted", false);
		Page<ProductSeries> res = productSeriesService.selectPage(rowBounds, example);
		res.getData().forEach(data -> data.setPhoto(FileUtils.getFileUrls(data.getPhoto())));
		res.setDraw(draw);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", res);

		return modelAndView;
	}

	@RequestMapping(value = "remove")
	@Log(module = "产品系列管理", method = "删除产品类型")
	public @ResponseBody String remove(@RequestParam("id") Long id) {
		if (id == null)
			return new Response(ResponseCode.REQUEST_ERROR).toJson();
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("id", id);

		List<ProductSeries> selectByExample = productSeriesService.selectByExample(example);
		if (selectByExample.size() > 0) {
			ProductSeries productSeries = selectByExample.get(0);
			productSeries.setIsDeleted(true);
			productSeriesService.update(productSeries);
		}
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "create")
	@Log(module = "产品系列管理", method = "添加产品系列")
	public @ResponseBody
    String create(@RequestBody ProductSeries productSeries) {
		if (0 == productSeries.getParentId()) {
			productSeries.setLevel(1);
		} else {
			ProductSeries parent = productSeriesService.get(productSeries.getParentId());
			productSeries.setLevel(parent.getLevel()+1);
		}
		productSeriesService.insert(productSeries);
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "edit")
	@Log(module = "产品类型管理", method = "编辑产品类型")
	public @ResponseBody
    String edit(@RequestBody ProductSeries productSeries) {
		Long id = productSeries.getId();
		Example example = new Example(ProductSeries.class);
		example.createCriteria().andEqualTo("id", id);
		ProductSeries oProductType = productSeriesService.selectByExample(example).get(0);

		oProductType.setNameCn(productSeries.getNameCn());
		oProductType.setNameEn(productSeries.getNameEn());
		oProductType.setNameEs(productSeries.getNameEs());

		if (StringUtils.isNotBlank(productSeries.getPhoto())) {
			oProductType.setPhoto(productSeries.getPhoto());
		}
		if (null != productSeries.getParentId() && 0 < productSeries.getParentId()) {
			oProductType.setParentId(productSeries.getParentId());
		}

		productSeriesService.update(oProductType);
		return new Response(ResponseCode.SUCCESS).toJson();
	}
	
    @RequestMapping(value = "/getSelect")
    @Log(module = "产品类型管理", method = "获取产品类型下拉列表")
    public ModelAndView getSelect(HttpServletRequest request, HttpServletResponse response) {

        Example example = new Example(ProductSeries.class);
        example.createCriteria().andEqualTo("isDeleted", false);
        List<ProductSeries> list = productSeriesService.selectByExample(example);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("option", list);

        return modelAndView;
    }

}