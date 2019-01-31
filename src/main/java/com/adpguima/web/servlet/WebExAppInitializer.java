package com.adpguima.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.adpguima.web.config.AdminWebContextConfig;
import com.adpguima.web.config.CustomerWebContextConfig;
import com.adpguima.web.config.RootContextConfig;

public class WebExAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext dispatcherWebContext = new AnnotationConfigWebApplicationContext();
		dispatcherWebContext.register(CustomerWebContextConfig.class);

		ServletRegistration.Dynamic dispatcherRegistration = servletContext.addServlet("dispatcher",
				new DispatcherServlet(dispatcherWebContext));
		dispatcherRegistration.setLoadOnStartup(1);
		dispatcherRegistration.addMapping("/");

		AnnotationConfigWebApplicationContext adminWebContext = new AnnotationConfigWebApplicationContext();
		adminWebContext.register(AdminWebContextConfig.class);

		ServletRegistration.Dynamic adminRegistration = servletContext.addServlet("admin_dispatcher",
				new DispatcherServlet(adminWebContext));
		adminRegistration.setLoadOnStartup(1);
		adminRegistration.addMapping("/admin/*");

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}

}
