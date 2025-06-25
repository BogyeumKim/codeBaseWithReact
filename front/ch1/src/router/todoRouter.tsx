import { Children, lazy, Suspense } from "react";
import { Loading } from "./root";

const TodoIndex = lazy(() => import("../pages/todo/indexPage"));
const TodoList = lazy(() => import("../pages/todo/listPage"));

const todoRouter = () => {
  return {
    path: "todo",
    Component: TodoIndex,
    children: [
      {
        path: "list",
        element: (
          <Suspense fallback={<Loading />}>
            <TodoList />
          </Suspense>
        ),
      },
    ],
  };
};

export default todoRouter;
