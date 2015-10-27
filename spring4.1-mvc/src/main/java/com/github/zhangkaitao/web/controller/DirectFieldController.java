package com.github.zhangkaitao.web.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: zhangkaitao
 * Date: 14-8-6
 * Time: ����8:45
 * Version: 1.0
 */
@RestController
public class DirectFieldController {

    @RequestMapping("/directField")
	@ResponseBody
    public String directFieldInject( MyUser user) {
        System.out.println(user);
        return user.toString();
    }
//
//    @InitBinder
//    public void initBinder(DataBinder dataBinder) {
//        dataBinder.initDirectFieldAccess();
//    }

    static class MyUser implements Serializable {
        private int id;
        private String name;

        @Override
        public String toString() {
            return "MyUser{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
