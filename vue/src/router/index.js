import { createRouter, createWebHistory } from 'vue-router'

import TeacherLayout from "@/layout/TeacherLayout";
import AdminLayout from "@/layout/AdminLayout";
import StudentLayout from "@/layout/StudentLayout";

const routes = [
  {
    path: '/',
    redirect:"/login",
  },
  {
    path: '/admin',
    component: AdminLayout,
    redirect:"/welcome",
    children: [
      {
        path: '/welcome',
        name: 'welcome',
        component: () => import("@/components/Welcome"),
        meta:{
          title:'欢迎登录Stars在线考试系统'
        }
      },
      {
        path: '/adminManage',
        name: 'adminManage',
        component: () => import("@/components/admin/AdminManage"),
        meta:{
          title:'Stars管理员管理'
        }
      },
      {
        path: '/adminExamManage',
        name: 'adminExamManage',
        component: () => import("@/components/admin/AdminExamManage"),
        meta:{
          title:'Stars考试管理'
        }
      },
      {
        path:'/grade', //学生成绩
        component: () => import('@/components/charts/Grade'),
        meta:{
          title:'Stars学生成绩'
        }
      },
      {
        path: '/teacherManage',
        name: 'teacherManage',
        component: () => import("@/components/admin/TeacherManage"),
        meta:{
          title:'Stars教师管理'
        }

      },
      {
        path: '/adminStudentManage',
        name: 'adminStudentManage',
        component: () => import("@/components/admin/AdminStudentManage"),
        meta:{
          title:'Stars学生管理'
        }
      },
      {
        path: '/adminAddQuestions',
        name: 'adminAddQuestions',
        component: () => import("@/components/admin/adminAddQuestions"),
        meta:{
          title:'Stars添加题库'
        }
      },
      {
        path: '/adminExamFunction',
        name: 'adminExamFunction',
        component: () => import("@/components/admin/AdminExamFunction"),
        meta:{
          title:'Stars考试功能介绍'
        }
      },
      {
        path: '/adminExamManage',
        name: 'adminExamManage',
        component: () => import("@/components/admin/AdminExamManage"),
        meta:{
          title:'Stars考试管理'
        }
      },
      {
        path: '/aGradePart',
        name: 'adminGradePart',
        component: () => import("@/components/charts/AGradePart"),
        meta:{
          title:'Stars成绩分析'
        }
      },
      {
        path: '/adminPaperManage',
        name: 'adminPaperManage',
        component: () => import("@/components/admin/AdminPaperManage"),
        meta:{
          title:'Stars试卷管理'
        }
      },
      {
        path: '/adminScore',
        name: 'adminScore',
        component: () => import("@/components/admin/AdminScore"),
        meta:{
          title:'Stars学生成绩'
        }
      },
      {
        path: '/adminScoreOne',
        name: 'adminScoreOne',
        component: () => import("@/components/admin/AdminScoreOne"),
        meta:{
          title:'Stars学生成绩'
        }
      },
      {
        path: '/adminScorePartOne',
        name: 'adminScorePartOne',
        component: () => import("@/components/admin/AdminScorePartOne"),
        meta:{
          title:'Stars科目成绩'
        }
      },
      {
        path: '/adminScorePart',
        name: 'adminScorePart',
        component: () => import("@/components/admin/AdminScorePart"),
        meta:{
          title:'Stars科目成绩'
        }

      },
      {
        path: '/gradePart',
        name: 'gradePart',
        component: () => import("@/components/charts/GradePart"),
        meta:{
          title:'Stars成绩分析表'
        }
      },
      {
        path: '/adminSetPaper',
        name: 'adminSetPaper',
        component: () => import("@/components/admin/AdminSetPaper"),
        meta:{
          title:'Stars查看试卷'
        }
      },
      {
        path: '/adminJudgeQuestion',
        name: 'adminJudgeQuestion',
        component: () => import("@/components/admin/AdminJudgeQuestion"),
        meta:{
          title:'Stars判断题题库'
        }
      },
      {
        path: '/adminMultiQuestion',
        name: 'adminMultiQuestion',
        component: () => import("@/components/admin/AdminMultiQuestion"),
        meta:{
          title:'Stars选择题题库'
        }
      },
      {
        path: '/adminFillQuestion',
        name: 'adminFillQuestion',
        component: () => import("@/components/admin/AdminFillQuestion"),
        meta:{
          title:'Stars填空题题库'
        }
      },
    ]
  },
  {
    path: '/teacher',
    component: TeacherLayout,
    redirect:"/home",
    children:[
      {
        path: '/home',
        name: 'Home',
        component: () => import("@/components/Home")
      },
      {
        path:'/grade', //学生成绩
        component: () => import('@/components/charts/Grade'),
        meta:{
          title:'Stars学生成绩'
        }
      },
      {
        path: '/teacherInfo',
        name: 'teacherInfo',
        component: () => import("@/components/personInfos/TeacherInfo"),
        meta:{
          title:'Stars教师信息'
        }
      },
      {
        path: '/studentManage', //学生管理界面
        name: 'studentManage',
        component: () => import("@/components/teacher/StudentManage"),
        meta:{
          title:'Stars学生管理'
        }
      },
      {
        path: '/examFunction',
        name: 'examFunction',
        component: () => import("@/components/teacher/ExamFunction"),
        meta:{
          title:'Stars考试功能介绍'
        }
      },
      {
        path: '/examManage',
        name: 'examManage',
        component: () => import("@/components/teacher/ExamManage"),
        meta:{
          title:'Stars考试管理'
        }
      },
      {
        path: '/paperManage',
        name: 'paperManage',
        component: () => import("@/components/teacher/PaperManage"),
        meta:{
          title:'Stars试卷管理'
        }
      },
      {
        path: '/score',
        name: 'score',
        component: () => import("@/components/teacher/Score"),
        meta:{
          title:'Stars学生成绩'
        }
      },
      {
        path: '/scoreOne',
        name: 'scoreOne',
        component: () => import("@/components/teacher/ScoreOne"),
        meta:{
          title:'Stars学生个人成绩'
        }
      },
      {
        path: '/scorePart',
        name: 'scorePart',
        component: () => import("@/components/teacher/ScorePart"),
        meta:{
          title:'Stars科目成绩'
        }
      },
      {
        path: '/scorePartOne',
        name: 'scorePartOne',
        component: () => import("@/components/teacher/ScorePartOne"),
        meta:{
          title:'Stars单科成绩'
        }
      },
      {
        path: '/gradePart',
        name: 'gradePart',
        component: () => import("@/components/charts/GradePart"),
        meta:{
          title:'Stars科目成绩分析'
        }
      },
      {
        path: '/setPaper',
        name: 'setPaper',
        component: () => import("@/components/teacher/SetPaper"),
        meta:{
          title:'Stars设置试卷'
        }
      },
      {
        path: '/addQuestions',
        name: 'addQuestions',
        component: () => import("@/components/teacher/AddQuestions"),
        meta:{
          title:'Stars自动组卷'
        }
      },
      {
        path: '/judgeQuestion',
        name: 'judgeQuestion',
        component: () => import("@/components/teacher/JudgeQuestion"),
        meta:{
          title:'Stars判断题题库'
        }
      },
      {
        path: '/multiQuestion',
        name: 'multiQuestion',
        component: () => import("@/components/teacher/MultiQuestion"),
        meta:{
          title:'Stars选择题题库'
        }
      },
      {
        path: '/fillQuestion',
        name: 'fillQuestion',
        component: () => import("@/components/teacher/FillQuestion"),
        meta:{
          title:'Stars填空题题库'
        }
      },
    ]
  },
  {
    path: '/student',
    name: 'student',
    component: StudentLayout,
    children: [
      {
        path: '/studentIndex',
        name: 'studentIndex',
        component: () => import("@/components/student/StudentIndex"),
        meta:{
          title:'Stars学生主页'
        }
      },
      {
        path: '/studentInfo',
        name: 'studentInfo',
        component: () => import("@/components/personInfos/StudentInfo"),
        meta:{
          title:'Stars学生个人信息'
        }
      },
      {
        path: '/examMsg',
        name: 'examMsg',
        component: () => import("@/components/student/examMsg"),
        meta:{
          title:'Stars考试信息'
        }
      },
      {
        path: '/scoreTable',
        name: 'scoreTable',
        component: () => import("@/components/student/scoreTable"),
        meta:{
          title:'Stars考试成绩'
        }
      },
    ]
  },
  {
    path: '/answer',
    name: 'answer',
    component: () => import("@/components/student/answer"),
    meta:{
      title:'Stars答题界面'
    }
  },
  {
    path: '/answerScore',
    name: 'answerScore',
    component: () => import("@/components/student/answerScore"),
    meta:{
      title:'Stars分数结算'
    }
  },
  {
    path: '/login',
    name: 'login',
    component: () => import("@/components/Login"),
    meta:{
      title:'欢迎登录Stars在线考试系统'
    }
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import("@/components/Register"),
    meta:{
      title:'欢迎注册Stars在线考试系统'
    }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
