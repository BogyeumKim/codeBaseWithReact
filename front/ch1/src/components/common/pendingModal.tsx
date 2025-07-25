const PendingModal = () => {
  return (
    <div
      className={`fixed top-0 left-0 z-[1055] flex h-full w-full items-center-safe justify-center `}
      style={{ backgroundColor: "rgba(169, 169, 169, 0.7)" }}
    >
      <div className=" bg-white rounded-3xl opacity-100 min-w-min h-1/4 min-w-[600px] flex justify-center items-center ">
        <div className="text-4xl font-extrabold text-orange-400 m-20">
          Pending.....
        </div>
      </div>
    </div>
  );
};
export default PendingModal;
