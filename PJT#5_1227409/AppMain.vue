<script setup>
import IconMain from '@/components/icons/IconMain.vue';
import MainChild from '@/components/main/MainChild.vue';

import {ref} from 'vue';

// 이렇게 ref를 해서 변수를 선언하고 <div>에 넣으면 MainChild 에서 값을 사용하기 위해서 : (v-bind) 를 해줘야 한다.
const myName = ref("하이쌤")
const myMessage = ref("싸피 방문을 환영합니다")
// 이 자식이 바뀐다.

// 자식 (MainChild)이 보내준 msg를 인자로 받는다.
const callBack = (msg) => {
    console.log("자식 이벤트 발생!!!", msg);
    myMessage.value = msg;
};

const modifyName = (cname) => {
    myName.value = cname;
};

//...으로 받고
const receiveArgs = (...msg) => {
    console.log(msg);
    // 한 칸 뛰움으로써 join 해준다.
    myMessage.value = msg.join(" ");
};
</script>

<template>
    <div>
        <h1><IconMain/>메인 화면입니다.</h1>
        <!-- 자식에서 생성한 부모 데이터 변경 버튼을 누르면 부모에서 callBack이라는 함수가 실행되도록 할거야! -->
         <!-- 자식의 emitEvent의 두 번째 인자인 취업을 축하합니다 라는 메시지를 callBack 함수가 받는다. -->
        <MainChild :my-name="myName" :my-msg="myMessage" @emit-event="callBack" @change-name="modifyName" @send-args="receiveArgs"/>
    </div>
</template>

<style scoped>

</style>