package indi.dsb.hardware.common;

/**
 * 乐欣API常量
 * 
 * @author admin
 *
 */
public class ApiContant {

	/**
	 * 应用id
	 */
	public static final String APP_ID = "lxc938402f46360603";
	/**
	 * 应用密钥
	 */
	public static final String APP_SECRET = "9e0e066ede17485e7bef8193cdaf43e3";

	/**
	 * 请求地址固定前缀
	 */
	public static final String BASE_URL = "http://ehr.honey-lovely.cn/YYTV1";

	/**
	 * 商学院的url
	 */
	public static final String SCHOOL_INFO_BASE_URL = "http://file.lexinvip.com:9001/JElastic/yyt";
	
	/**
	 * 商学院的key
	 */
	public static final String SCHOOL_INFO_KEY = "2b52caecce7cf01703a0d451b945a4a8";

    /**
     * 文件存储根路径
     */
    public static final String SAVE_FILE_PATH = "F://files//center//";

    /**
     * 文件读取根路径
     */
    public static final String READ_FILE_PATH = "http://14.23.60.186:8082/files/center";

    /**
     * OA请求地址固定前缀
     */
    public static final String OA_API_BASE_URL = "http://moa.honey-lovely.com:8087/oa-api";
    
    /**
     * 同步接口
     */
    public static final String SERVICE_OA_WORKFLOW_SAVEPOSTPONE = OA_API_BASE_URL + "/workflow/savePostpone";
    
    /**
     * 查询接口
     */
    public static final String SERVICE_OA_WORKFLOW_GETWORKFLOW = OA_API_BASE_URL + "/workflow/getWorkflow";
    /**
     * 推送消息接口
     */
    public static final String SERVICE_OA_WECHAT_SEND_WECHAT = OA_API_BASE_URL + "/wechat/sendWeChat";

	/* =======================请求接口具体后缀 begin======================= */

	/**
	 * 获取tokenw
	 */
	public static final String SERVICE_TOKEN = "/Token/get";

	/**
	 * 获取token对象
	 */
	public static final String SERVICE_TOKEN_GET_OBJECT = "/Token/get_object";

	/**
	 * 产品品类列表接口
	 */
	public static final String SERVICE_PRODUCT_GET_TYPE = "/Product/get_type";

	/**
	 * 产品品牌列表接口
	 */
	public static final String SERVICE_PRODUCT_GET_BRAND = "/Product/get_brand";

	/**
	 * 产品品牌列表接口
	 */
	public static final String SERVICE_PRODUCT_GET_LIST = "/Product/get_list";

	/**
	 * 新品推荐列表接口
	 */
	public static final String SERVICE_PRODUCT_GET_NEW_LIST = "/Product/get_new_list";

	/**
	 * 爆款推荐列表接口
	 */
	public static final String SERVICE_PRODUCT_GET_HOT_LIST = "/Product/get_hot_list";

	/**
	 * 最近促销方案列表接口
	 */
	public static final String SERVICE_PRODUCT_GET_PROMOTITION_LIST = "/Product/get_last_promotion";

	/**
	 * 促销方案详情接口
	 */
	public static final String SERVICE_PRODUCT_GET_PROMOTITION_DETAIL = "/Product/get_promotion_products";

	/**
	 * 商品详情
	 */
	public static final String SERVICE_PRODUCT_GET_INFO = "/Product/get_info";

	/**
	 * 门店商品档案查询接口
	 */
	public static final String SERVICE_PRODUCT_GET_STORE_PRODUCT = "/Product/get_store_product";

	/* ===================user start================================= */
	/**
	 * 员工列表接口
	 */
	public static final String SERVICE_USER_GET_SIMPLE_ALL = "/User/get_simple_all";
	/**
	 * 根据部门ID查找所属部门员工
	 */
	public static final String SERVICE_USER_GET_LIST = "/User/get_list";
	/**
	 * 用户登录
	 */
	public static final String SERVICE_USER_LOGIN = "/User/get_login_info";
	/* ===================user end================================= */

	/**
	 * 部门列表接口
	 */
	public static final String SERVICE_DEPARTMENT_GET_LIST = "/Department/get_simple_all";

	/**
	 * 部门详情（有问题）
	 */
	public static final String SERVICE_DEPARTMENT_DETAILS = "/Department/get_simple_all";

	/* =======================store start======================= */
	// 客户列表接口
	public static final String SERVICE_STORE_GET_LIST = "/Store/get_list";
	// 客户回顾信息接口 参数(String storecode 门店编码)
	public static final String SERVICE_STORE_GET_REVIEW = "/Store/get_review";
	// 门店数据接口
	public static final String SERVICE_STORE_GET_INFO = "/Store/get_info";

	public static final String SERVICE_STORE_SUPPLY_LIST = "/Store/get_supply_list";

	// 3.2 修改客户/门店资料接口
	public static final String SERVICE_STORE_UPDATE_DATA = "/Store/update_data";

	/**
	 * 补货清单
	 */
	public static final String SERVICE_STORE_GET_SUPPLY_LIST = "/Store/get_supply_list";

	/* =======================store end======================= */
	/* =======================信息台模块 start======================= */

	// 投诉列表
	public static final String SERVICE_COMPLAINT_GET_LIST = "/Info/get_complaint_list";

	// 添加投诉
	public static final String SERVICE_COMPLAINT_ADD = "/Info/add_complaint";

	// 反馈列表
	public static final String SERVICE_FEEDBACK_GET_LIST = "/Info/get_feedback_list";

	// 新增反馈
	public static final String SERVICE_FEEDBACK_ADD = "/Info/add_feedback";

	// 一级问题列表
	public static final String SERVICE_QUESTION_CLASS_TOP_LIST = "/Info/get_top_class";

	// 子级问题列表
	public static final String SERVICE_QUESTION_CLASS_CHILD_LIST = "/Info/get_child_class";

	// 反馈详情
	public static final String SERVICE_FEEDBACK_GET_DETAILS = "/Info/get_feedback";

	// 投诉详情
	public static final String SERVICE_COMPLAINT_GET_DETAILS = "/Info/get_complaint";

	/* =======================信息台模块 end======================= */

	/* =======================客户档案模块 start======================= */

	// 7.1 客户档案查询列表
	public static final String SERVICE_RECORD_GET_LIST = "/Record/get_list";

	// 7.2 客户基本信息列表
	public static final String SERVICE_RECORD_GET_INFO = "/Record/get_info";

	// 7.3 客户签约合同
	public static final String SERVICE_RECORD_GET_CONTRACT = "/Record/get_contract";

	// 7.4 客户档案附件
	public static final String SERVICE_RECORD_GET_ATTACHMENT = "/Record/get_attachment";

	// 7.5 客户自购商品信息
	public static final String SERVICE_RECORD_GET_GOODS = "/Record/get_goods";

	// 7.6 省份列表
	public static final String SERVICE_RECORD_GET_PROVINCE = "/Record/get_province";

	// 7.7 城市列表
	public static final String SERVICE_RECORD_GET_CITY = "/Record/get_city";

	// 7.8 城市列表
	public static final String SERVICE_RECORD_GET_FILIALE = "/Record/get_filiale";

	/* =======================客户档案模块 end======================= */

	/* =======================客户分析模块 start======================= */
	// 9.1 客户基本信息
	public static final String SERVICE_STORE_ANALYSIS_GET_INFO = "/StoreAnalysis/get_info";

	// 9.2 销售金额占比分析
	public static final String SERVICE_STORE_ANALYSIS_GET_SALE_RATE = "/StoreAnalysis/get_sale_rate";

	// 9.3 商品类别占比分析
	public static final String SERVICE_STORE_ANALYSIS_GET_PRODUCT_TYPE_RATE = "/StoreAnalysis/get_product_type_rate";

	// 9.4 账上余额占比分析
	public static final String SERVICE_STORE_ANALYSIS_GET_ACCOUNT_AMOUNT_RATE = "/StoreAnalysis/get_account_amount_rate";

	// 9.5 客户分析明细报表
	public static final String SERVICE_STORE_ANALYSIS_GET_DETAIL = "/StoreAnalysis/get_detail";

	/* =======================客户分析模块 end======================= */

	// 4.11 添加门店推荐订单接口
	public static final String SERVICE_PRODUCT_ADD_RECOMMEND_ORDER = "/Product/add_recommend_order";

	/* =======================业绩分析模块 start======================= */

	// 12.1 本月充值单信息
	public static final String SERVICE_RESULT_ANALYSIS_GET_RECHARGE_LIST = "/ResultAnalysis/get_recharge_list";

	// 12.2 商品top10信息
	public static final String SERVICE_RESULT_ANALYSIS_GET_PRODUCT_TOP10 = "/ResultAnalysis/get_product_top10";

	// 12.3 业绩分析
	public static final String SERVICE_RESULT_ANALYSIS_GET_ANALYSIS = "/ResultAnalysis/get_analysis";

	// 12.4 业绩信息
	public static final String SERVICE_RESULT_ANALYSIS_GET_INFO = "/ResultAnalysis/get_info";

	// 12.5 客户信息
	public static final String SERVICE_RESULT_ANALYSIS_GET_RECORD_INFO = "/ResultAnalysis/get_record_info";

	/* =======================业绩分析模块 end======================= */

	/* ======================= 商学院模块 start ======================= */

	// 8.1 学习任务列表
	public static final String SERVICE_SCHOOL_GET_TASK = "/School/get_task";

	// 8.2 课程推荐列表
	public static final String SERVICE_SCHOOL_GET_RECOMMEND = "/School/get_recommend";

	// 8.3 用户积分查询
	public static final String SERVICE_SCHOOL_GET_INTERGRAL = "/School/get_integral";

	// 8.4 用户考试平均分
	public static final String SERVICE_SCHOOL_GET_AVERAGE = "/School/get_average";

	// 8.5 任务详情
	public static final String SERVICE_SCHOOL_GET_TASK_INFO = "/School/get_task_info";

	// 8.6 课程详情
	public static final String SERVICE_SCHOOL_GET_COURSE = "/School/get_course";

	/* ======================= 商学院模块 end ======================= */

	/* ======================= 工作汇报 start ======================= */
	// 11.1 月报增加
	public static final String SERVICE_REPORT_ADD_MONTH = "/Report/add_month";

	// 11.2 周报增加
	public static final String SERVICE_REPORT_ADD_WEEK = "/Report/add_week";

	// 11.3 周报业绩
	public static final String SERVICE_REPORT_GET_WORKREPORT_RESULT = "/Report/get_workreport_result";

	/* =======================13.诊断报告模块 start ======================= */

	// 13.1 指标列表
	public static final String SERVICE_ANALYSIS_REPORT_GET_DICT = "/AnalysisReport/get_dict";

	// 13.2 指标值
	public static final String SERVICE_ANALYSIS_REPORT_GET_REPORT = "/AnalysisReport/get_report";

	// 13.3 用户指标
	public static final String SERVICE_ANALYSIS_REPORT_GET_USER = "/AnalysisReport/get_user";

	/* =======================13.诊断报告模块 end ======================= */

	/* =======================工作汇报 end ======================= */

	/* ======================= 10.任务管理模块 start ======================= */

	// 10.1 回访任务管理
	public static final String SERVICE_TASK_ADD_REPLY = "/Task/add_reply";

	// 10.1 回访记录查询
	public static final String SERVICE_TASK_GET_REPLY = "/Task/get_reply";

	// 10.2 添加出差任务（巡店任务）
	public static final String SERVICE_TASK_ADD_TASK = "/Task/add_task";

	// 10.4 更新任务记录
	public static final String SERVICE_TASK_UPDATE_TASK = "/Task/update_task";

	// 10.5 获取回访详情
	public static final String SERVICE_TASK_GET_REPLY_DETAIL = "/Task/get_reply_detail";

	/* ======================= 10.任务管理模块 end ======================= */

	/* ======================= 商学院接口 ======================= */

	// 获取商学院详情URL
	public static final String SERVICE_SCHOOL_INFO_GET_URL = "/url";


	/* ======================= 商学院接口 ======================= */

	/* =======================具体请求接口的后缀 end======================= */

}
