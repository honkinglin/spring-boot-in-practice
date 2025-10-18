import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: 'Spring Boot 实战',
  description: '基于 Spring Boot 3.4.x 与 Java 21 的实战与示例',
  rewrites: {
    'index.md': '/chapters/01-introduction',
  },
  themeConfig: {
    nav: [],
    sidebar: {
      '/': [
        {
          text: '目录',
          items: [
            { text: '第1章 简介', link: '/chapters/01-introduction' },
            { text: '第2章 常用任务', link: '/chapters/02-common-tasks' },
            { text: '第3章 数据访问', link: '/chapters/03-data-access' },
            { text: '第4章 自动配置与监控', link: '/chapters/04-autoconfiguration-and-actuator' },
            { text: '第5章 应用安全', link: '/chapters/05-application-security' },
            { text: '第6章 进阶安全', link: '/chapters/06-advanced-security' },
            { text: '第7章 REST 服务', link: '/chapters/07-rest-services' },
            { text: '第8章 响应式开发', link: '/chapters/08-reactive-development' },
            { text: '第9章 部署', link: '/chapters/09-deployment' },
            { text: '第10章 Kotlin/Native/GraphQL', link: '/chapters/10-kotlin-native-graphql' },
            { text: '附录A 生成与构建', link: '/appendices/A-generating-and-building' },
            { text: '附录B Spring MVC 与 Thymeleaf', link: '/appendices/B-springmvc-thymeleaf' }
          ]
        }
      ]
    },
    socialLinks: [
      { icon: 'github', link: 'https://github.com/honkinglin/spring-boot-in-practice' }
    ]
  }
})
