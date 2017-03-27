package com.aohuan.dodo.java_demo.common.tools.gen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class GenerServiceCode {

	public static final String filePathPrefix = "E:\\AppKey\\_DoCode\\";
	
	public static final String packageName = "com.lei1tec.service.dodo";

	public static ArrayList<ServiceCodeBean> beanList = new ArrayList<>();
	
//	public static String[] strArr = {"zk_ad", "zk_ad_cate", "zk_angel_user", "zk_demand", "zk_demand_bidding", "zk_demand_collect", "zk_demand_comment", "zk_demand_dismantle", "zk_demand_praise", "zk_goods", "zk_goods_grade", "zk_goodstype", "zk_hatch_project", "zk_merchant", "zk_order_form", "zk_orderform_invoice", "zk_post", "zk_post_comment", "zk_post_praise", "zk_post_type", "zk_project_diagnose", "zk_project_roadshow", "zk_roadshow_grade", "zk_roadshow_record", "zk_shop", "zk_short_message", "zk_short_video", "zk_short_video_collect", "zk_short_video_praise", "zk_sku", "zk_use_integral", "zk_user", "zk_user_focus", "zk_video_collect", "zk_video_comment", "zk_video_like"};
	
	public static String[] strArr = {};
	
	

	public void action() {
		if(!before())
			return;
		run();
		after();
	}

	private boolean before() {
		ArrayList<String> strList = MysqlTableReader.getTables();
		if(strList == null){
			System.out.println("mysql null");
			return false;
		}
		strArr = (String[])strList.toArray(new String[strList.size()]);
				
		for (String string : strArr) {
			String[] split2 = string.split("_");
			String lei = "";
			for (int i=1 ;i<split2.length;i++) {
				String string2=split2[i];
				char[] cs = string2.toCharArray();
				cs[0] -= 32;
				lei+=String.valueOf(cs);
			}
			ServiceCodeBean scb = new ServiceCodeBean();
			scb.setServiceName(lei+"Service");
			scb.setMapperName(lei+"Mapper");
			beanList.add(scb);
//			System.out.println(lei);
		}
		return true;
	}

	private void run() {
		
		for(int i=0; i<beanList.size(); i++){
			doItem(beanList.get(i) , packageName);
		}
	}

	private void doItem(ServiceCodeBean serviceCodeBean, String packageName) {

		String filePath = filePathPrefix + serviceCodeBean.getServiceName() + ".java";

		try {
			StringBuffer bf = new StringBuffer();
			// 接受字符串数组
			bf.append("package "+packageName+";\n");
			bf.append("import java.util.List;\n");
			bf.append("import javax.annotation.Resource;\n");
			bf.append("import org.springframework.stereotype.Service;\n");
			bf.append("import com.lei1tec.dao.DaoSupport;\n");
			bf.append("import com.lei1tec.entity.Page;\n");
			bf.append("import com.lei1tec.entity.PageData;\n");
			bf.append("import com.lei1tec.util.DateUtil;\n");
			bf.append("@Service(\"shopUserService\")\n");
			bf.append("public class " + serviceCodeBean.getServiceName() + " {\n");
			bf.append("	@Resource(name = \"daoSupport\")\n");
			bf.append("	private DaoSupport dao;\n");
			bf.append("	/*\n");
			bf.append("	 * 通过id获取数据\n");
			bf.append("	 */\n");
			bf.append("	public PageData findById(PageData pd) throws Exception {\n");
			bf.append("		return (PageData) dao.findForObject(\"" + serviceCodeBean.getMapperName()
					+ ".find\", pd);\n");
			bf.append("	}\n");
			bf.append("	/*\n");
			bf.append("	\n");
			bf.append("	/*\n");
			bf.append("	 * 列表\n");
			bf.append("	 */\n");
			bf.append("	@SuppressWarnings(\"unchecked\")\n");
			bf.append("	public List<PageData> searchlist(PageData pd) throws Exception {\n");
			bf.append("		return (List<PageData>) dao.findForList(\"" + serviceCodeBean.getMapperName()
					+ ".find\", pd);\n");
			bf.append("	}\n");
			bf.append("	//分页查询\n");
			bf.append("	@SuppressWarnings(\"unchecked\")\n");
			bf.append("	public List<PageData> searchlistPage(Page page) throws Exception {\n");
			bf.append("		return (List<PageData>) dao.findForList(\"" + serviceCodeBean.getMapperName()
					+ ".searchlistPage\", page);\n");
			bf.append("	}\n");
			bf.append("	/*\n");
			bf.append("	 * 保存\n");
			bf.append("	 */\n");
			bf.append("	public void save(PageData pd) throws Exception {\n");
			bf.append("		pd.put(\"CreateTime\", DateUtil.getTime());\n");
			bf.append("		dao.save(\"" + serviceCodeBean.getMapperName() + ".save\", pd);\n");
			bf.append("	}\n");
			bf.append("	/*\n");
			bf.append("	 * 修改\n");
			bf.append("	 */\n");
			bf.append("	public void edit(PageData pd) throws Exception {\n");
			bf.append("		dao.update(\"" + serviceCodeBean.getMapperName() + ".edit\", pd);\n");
			bf.append("	}\n");
			bf.append("	/*\n");
			bf.append("	 * 删除\n");
			bf.append("	 */\n");
			bf.append("	public void delete(PageData pd) throws Exception {\n");
			bf.append("		dao.delete(\"" + serviceCodeBean.getMapperName() + ".delete\", pd);\n");
			bf.append("	}\n");
			bf.append("}\n");

			FileWriter fw = new FileWriter(filePath, false);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println(bf.toString());
			bw.append(bf.toString());
			bw.close();
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void after() {

	}

}
