import http from 'k6/http';
import { check } from "k6";
import { statusCheck200 } from "./util/statusCheck.js";

export let options = {
    stages: [
        {duration: "30s", target: 100}
    ]
};

export default function () {
    const username = "5mIEbFU7jhcC1CG2";
    const response = http.get(`http://localhost:8080/accounts/${username}`);

    check(response, statusCheck200);
};
