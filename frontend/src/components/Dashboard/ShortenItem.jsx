import dayjs from 'dayjs';
import React, { useEffect, useState } from 'react'
import CopyToClipboard from 'react-copy-to-clipboard';
import { FaExternalLinkAlt, FaRegCalendarAlt } from 'react-icons/fa';
import { IoCopy } from 'react-icons/io5';
import { LiaCheckSolid } from 'react-icons/lia';
import { MdAnalytics, MdOutlineAdsClick } from 'react-icons/md';
import api from '../../api/api';
import { Link, useNavigate } from 'react-router-dom';
import { useStoreContext } from '../../contextApi/ContextApi';
import { Hourglass } from 'react-loader-spinner';
import Graph from './Graph';

const ShortenItem = ({ originalUrl, shortUrl, clickCount, createdDate }) => {
    const { token } = useStoreContext();
    const navigate = useNavigate();

    const [isCopied, setIsCopied] = useState(false);
    const [analyticToggle, setAnalyticToggle] = useState(false);
    const [loader, setLoader] = useState(false);
    const [selectedUrl, setSelectedUrl] = useState("");
    const [analyticsData, setAnalyticsData] = useState([]);

    // 🔥 NEW STATES
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    const subDomain = import.meta.env.VITE_REACT_FRONT_END_URL.replace(
        /^https?:\/\//,
        ""
    );

    const analyticsHandler = (shortUrl) => {
        setSelectedUrl(shortUrl);
        setAnalyticToggle(!analyticToggle);
    };

    // 🔥 API CALL WITH DYNAMIC DATES
    const fetchMyShortUrl = async () => {
        if (!startDate || !endDate) {
            alert("Please select both start and end date");
            return;
        }

        setLoader(true);
        try {
            const start = `${startDate}T00:00:00`;
            const end = `${endDate}T23:59:59`;

            const { data } = await api.get(
                `/api/urls/analytics/${selectedUrl}?startDate=${start}&endDate=${end}`,
                {
                    headers: {
                        "Content-Type": "application/json",
                        Accept: "application/json",
                        Authorization: "Bearer " + token,
                    },
                }
            );

            setAnalyticsData(data);
            console.log(data);

        } catch (error) {
            navigate("/error");
            console.log(error);
        } finally {
            setLoader(false);
        }
    };

    // 🔥 DEFAULT DATE (Last 7 Days)
    useEffect(() => {
        const today = new Date();
        const lastWeek = new Date();

        lastWeek.setDate(today.getDate() - 7);

        setStartDate(lastWeek.toISOString().split("T")[0]);
        setEndDate(today.toISOString().split("T")[0]);
    }, []);

    return (
        <div className="bg-slate-100 shadow-lg border border-dotted border-slate-500 px-6 sm:py-1 py-3 rounded-md">

            {/* TOP SECTION */}
            <div className="flex sm:flex-row flex-col sm:justify-between w-full gap-5 py-5">

                {/* LEFT */}
                <div className="flex-1 space-y-2 overflow-x-auto">

                    <div className="flex items-center gap-2">
                        <Link
                            target='_'
                            className='text-[17px] font-semibold text-blue-600'
                            to={import.meta.env.VITE_REACT_FRONT_END_URL + "/s/" + shortUrl}
                        >
                            {subDomain + "/s/" + shortUrl}
                        </Link>
                        <FaExternalLinkAlt />
                    </div>

                    <h3 className="text-slate-700 text-[17px]">
                        {originalUrl}
                    </h3>

                    <div className="flex gap-8 pt-4">

                        <div className="flex items-center text-green-800 font-semibold">
                            <MdOutlineAdsClick className="me-1" />
                            {clickCount} {clickCount <= 1 ? "Click" : "Clicks"}
                        </div>

                        <div className="flex items-center text-slate-800 font-semibold">
                            <FaRegCalendarAlt className="me-1" />
                            {dayjs(createdDate).format("MMM DD, YYYY")}
                        </div>

                    </div>
                </div>

                {/* RIGHT BUTTONS */}
                <div className="flex items-center gap-4">

                    <CopyToClipboard
                        onCopy={() => setIsCopied(true)}
                        text={`${import.meta.env.VITE_REACT_FRONT_END_URL}/s/${shortUrl}`}
                    >
                        <div className="flex cursor-pointer items-center bg-blue-600 px-4 py-2 rounded text-white">
                            {isCopied ? "Copied" : "Copy"}
                            {isCopied ? <LiaCheckSolid /> : <IoCopy />}
                        </div>
                    </CopyToClipboard>

                    <div
                        onClick={() => analyticsHandler(shortUrl)}
                        className="flex cursor-pointer items-center bg-red-600 px-4 py-2 rounded text-white"
                    >
                        Analytics <MdAnalytics />
                    </div>

                </div>
            </div>

            {/* ANALYTICS SECTION */}
            {analyticToggle && (
                <div className="border-t pt-4">

                    {/* 🔥 DATE FILTER UI */}
                    <div className="flex gap-4 items-center mb-4 flex-wrap">
                        <input
                            type="date"
                            value={startDate}
                            onChange={(e) => setStartDate(e.target.value)}
                            className="border px-2 py-1 rounded"
                        />

                        <input
                            type="date"
                            value={endDate}
                            onChange={(e) => setEndDate(e.target.value)}
                            className="border px-2 py-1 rounded"
                        />

                        <button
                            onClick={fetchMyShortUrl}
                            className="bg-blue-600 text-white px-4 py-1 rounded"
                        >
                            Apply
                        </button>
                    </div>

                    {/* LOADER */}
                    {loader ? (
                        <div className="flex justify-center items-center min-h-[200px]">
                            <Hourglass height="50" width="50" />
                        </div>
                    ) : (
                        <>
                            {analyticsData.length === 0 ? (
                                <div className="text-center text-slate-600">
                                    No Data For This Time Period
                                </div>
                            ) : (
                               <div className="w-full max-w-[700px] h-[350px] mx-auto">
                                        <Graph graphData={analyticsData} />
                                                            </div>
                            )}
                        </>
                    )}

                </div>
            )}
        </div>
    );
};

export default ShortenItem;