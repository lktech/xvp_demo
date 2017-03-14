package com.lingke.xvp.demo.db.codegen;

import java.io.File;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.lingke.xvp.demo.XvpApp;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Created by cz on 2017-2-6.
 */
public class ActiveRecordGen {
	public static void main(String[] args) {
		// ActiveRecordConfig activeRecordConfig = new
		// ActiveRecordConfig("jdbc:mysql://rm-2zejri3r6l6wsqlu4o.mysql.rds.aliyuncs.com:3306/xiaovpu",
		// "xwp","Ruixuesoft2401");
		XvpApp app = new XvpApp();
		// DataSource ds = ;
		// activeRecordConfig.connect();

		File codeBase = new File(new File(PathKit.getRootClassPath())
				.getParentFile().getParentFile(), "src/main/java");
		String baseModelPkg = "com.lingke.xvp.demo.db.dao.base";
		String modelPkg = "com.lingke.xvp.demo..db.dao";
		String baseModelDir = new File(codeBase, baseModelPkg.replaceAll("\\.",
				"/")).getPath();
		String modelDir = new File(codeBase, modelPkg.replaceAll("\\.", "/"))
				.getPath();
		// // base model 所使用的包名
		// String baseModelPkg = "com.xiaovpu.openapi.impl.dao.base";
		//
		//
		// // base model 文件保存路径
		// String baseModelDir = new
		// File(PathKit.getRootClassPath()).getParentFile().getParentFile().getPath()
		// + "/src/main/java/" + PathKit.getPackagePath(activeRecordConfig);
		// // model 所使用的包名
		// String modelPkg = "model";
		// // model 文件保存路径
		// String modelDir = baseModelDir + "/..";
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://rm-2zejri3r6l6wsqlu4o.mysql.rds.aliyuncs.com:3306/xiaovpu");
		dataSource.setUser("xwp");
		dataSource.setPassword("Ruixuesoft2401");
		Generator gernerator = new Generator(dataSource, baseModelPkg, baseModelDir,
				modelPkg, modelDir);
		gernerator.generate();
		// System.out.println(codeBase);
	}
}
