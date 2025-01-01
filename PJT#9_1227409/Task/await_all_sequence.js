const axios = require("axios");

async function request(sub_path) {
  const url = "http://13.124.193.201:8844/" + sub_path;
  try {
    const response = await axios.get(url);
    return response.data;
  } catch (e) {
    console.log(e);
  }
}

const array = [
  { path: "a" },
  { path: "b" },
  { path: "c" },
  { path: "d" },
  { path: "e" },
];

async function test() {
  const async_fun_list = [];

  for (const item of array) {
    const async_fun = request(item.path);
    async_fun_list.push(async_fun);
  }

  for (const async_fun of async_fun_list) {
    const resolve = await async_fun;
    console.log(resolve);
  }
}

test();
