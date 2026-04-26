import { useQuery } from "@tanstack/react-query";
import api from "../api/api";

// 🔹 Fetch My Short URLs
export const useFetchMyShortUrls = (token, onError) => {
  return useQuery({
    queryKey: ["my-shortenurls", token], 
    queryFn: async () => {
      const res = await api.get("/api/urls/myurls", {
        headers: {
          Authorization: "Bearer " + token,
        },
      });
      return res.data;
    },
    select: (data) => {
      return data.sort(
        (a, b) => new Date(b.createdDate) - new Date(a.createdDate)
      );
    },
    onError,
    staleTime: 5000,
    enabled: !!token, 
  });
};


// 🔹 Fetch Total Clicks
export const useFetchTotalClicks = (token, onError) => {
  return useQuery({
    queryKey: ["url-totalclick", token],
    queryFn: async () => {
      const res = await api.get(
        "/api/urls/totalClicks?startDate=2026-01-01&endDate=2026-12-31",
        {
          headers: {
            Authorization: "Bearer " + token,
          },
        }
      );
      return res.data;
    },
    select: (data) => {
      return Object.keys(data).map((key) => ({
        clickDate: key,
        count: data[key],
      }));
    },
    onError,
    staleTime: 5000,
    enabled: !!token,
  });
};