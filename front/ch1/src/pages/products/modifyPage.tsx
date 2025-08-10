import { useLoaderData, useParams } from "react-router";
import ModifyComponent from "../../components/products/modifyComponent";
import { useQuery } from "@tanstack/react-query";
import jwtAxios from "../../util/jwtUtil";

function ModifyPage() {
  // const product: ProductDTO = useLoaderData();
  const { pno } = useParams(); // get id

  const { data, isPending, error } = useQuery({
    queryKey: ["product", pno],
    queryFn: async () => {
      const res = await jwtAxios.get(
        `http://localhost:8080/api/products/${pno}`
      );
      return res.data;
    },
    staleTime: 1000 * 60 * 60 * 24,
  });

  const product: ProductDTO = data;

  return (
    <div className="p-4 w-full bg-white">
      <div className="text-3xl font-extrabold">Products Modify Page</div>
      {product && <ModifyComponent product={product} />}
    </div>
  );
}

export default ModifyPage;
