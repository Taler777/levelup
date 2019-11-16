//package ru.levelup.junior.web;
//
//import ru.levelup.junior.dao.TasksDAO;
//import ru.levelup.junior.entities.Task;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.NoResultException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(urlPatterns = "/tasks")
//public class TasksListServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        EntityManagerFactory factory = StartupListener.getFactory(req.getServletContext());
//        EntityManager manager = factory.createEntityManager();
//        TasksDAO dao = new TasksDAO(manager);
//
//        try {
//            List<Task> tasks = dao.findAllTasks();
//
//            req.setAttribute("tasks", tasks);
//
//            req.getRequestDispatcher("/tasksList.jsp").forward(req, resp);
//        } catch (NoResultException notFound) {
//            req.getRequestDispatcher("/").forward(req, resp);
//        } finally {
//            manager.close();
//        }
//    }
//}
