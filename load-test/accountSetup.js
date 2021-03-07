import http from 'k6/http';
import { check } from "k6";
import { statusCheck200 } from "./util/statusCheck.js";

export let options = {
    stages: [
        {duration: "180s", target: 100}
    ]
};

export default function () {
    const response = http.post(`http://localhost:8080/account/generate`);

    check(response, statusCheck200);
};
