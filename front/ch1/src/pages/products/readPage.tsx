import axios from "axios";
import {
  useLoaderData,
  useParams,
  type LoaderFunctionArgs,
} from "react-router";
import ReadComponent from "../../components/products/readComponent";
import jwtAxios from "../../util/jwtUtil";
import PendingModal from "../../components/common/pendingModal";
import { useQuery } from "@tanstack/react-query";

// export async function loadProduct({ params }: LoaderFunctionArgs) {
//   const { pno } = params;

//   const res = await jwtAxios.get(`http://localhost:8080/api/products/${pno}`);

//   return res.data;
// }

function ReadPage() {
  // const product: ProductDTO = useLoaderData();
  // console.log(product);

  const { pno } = useParams();

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

  const product = data;

  return (
    <div className="w-full">
      <div>Product Read</div>
      {isPending && <PendingModal />}
      {product && <ReadComponent product={product} />}
    </div>
  );
}

export default ReadPage;
