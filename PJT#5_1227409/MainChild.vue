<script setup>
import MainChildItem from '@/components/main/MainChildItem.vue';

//from 뒤에는 ""로 가져온다.
import {ref} from "vue";

// 부모(AppMain.vue)의 my-msg의 값을 가져온다.
const props = defineProps(["myName","myMsg"]);
//AppMain(부모)로 부터 가져온 데이터를 사용하려면 일단 const props 로 받아보자.
//props.myMame 하면 하이쌤이 나온다.

const messages = ref ([
    {no:1, msg:"안녕 하세요"},
    {no:2, msg:"취업 축하해요"},
    {no:3, msg:"대박 나세요"}
]);


const emit = defineEmits(["changeName","sendArgs"])
const modifyName = (cname) => {
//  여기서 직접적으로 부모한테 받은 데이터를 바꾸면 안된다.
    emit("changeName", cname); //AppMain.vue (부모) 에다가 자식(MainChildItem)으로부터 받은 cname을 넘겨준다.
};

const sendArgs = () => {
    //여러개의 문자를 보낸다.
    emit("sendArgs", "오늘은", "날씨가","좋습니다");
};

</script>

<template>
    <div class="child">
        <h2>{{ myName }} 님 {{ myMsg }}</h2>
        <!-- MainChild에 messages를 뿌리고 싶다.-->

        <div>
            <!-- emitEvent라는 이름의 event를 버튼을 클릭하면 발생시킨다. -->
            <!-- 부모인 AppMain.vue에 가서 등록시켜주자. -->
            <!-- 취업을 축하합니다로 바꾼다. -->
            <button @click="$emit('emitEvent', '취업을 축하합니다')">부모 메시지 변경</button>

            <button @click="sendArgs">
               여러개의 인자를 넘길 경우
            </button>

        </div>

        <!-- 자식의 "인뇽" 이 반복문을 돌아서 3번 출력됨 -->
        <!-- msg의 객체의 값을 전달해야 하므로 :를 사용한다.  -->
        <!-- html에서는 변수명을 my-name으로 써야 한다. -->
        <!-- my-name이라는 변수에 부모에서 받아온 props.myName의 myName을 넣어서 MainChildItem에 보낸다. -->
         <!-- 태그는 PasCal로 쓰자. -->
        <MainChildItem v-for="msg in messages" :key="msg.no" :my-name="myName" :message-data="msg"
         @change-name="modifyName"
        />
        <!-- 자식으로부터 chage-name이라는 @(이벤트)가 오면 자기자신이 선언해준 modifyName을 호출하자. -->
    </div>
</template>

<style scoped>
.child{
    background-color: pink;
}
</style>