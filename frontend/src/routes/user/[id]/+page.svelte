<script>
	import { myToken } from "$lib/components/stores";
	import { error } from "@sveltejs/kit";
	import { onMount } from "svelte";

    export let data;

    let token = null;
    let onRender = false;
    let profile = null;
    let onError = null;
    myToken.subscribe(value => { token = value; });

    onMount(() => {
        update();
    });

    async function update(){
        let uri = `http://127.0.0.1:8080/profile/${data.id}`;
        if(token != null && token.publicPart != null) uri = uri + "?TOKEN=" + token.publicPart;
        let response = await fetch(uri, { method:"GET" });
        if(response.ok || response.status >= 200 && response.status < 300){
            profile = await response.json();
            onRender = true;
        } else {
            onError = true;
            throw new error(response.status, response.statusText);
        }
    }

</script>

<div class="flex flex-col">
    {#if onRender == true && onError == null}
    <div class="bg-slate-700 rounded-lg">
        <div class="flex flex-row p-6 rounded-lg">
        <div class="flex flex-col bg-slate-400 rounded-2xl p-4">
            <img class="rounded-3xl" src="http://127.0.0.1:8080/uploads/{profile.profilePic}">
            <div class="flex flex-row">
                <p class="p-2 pl-2">{profile.name}</p>
                <p class="pt-2">{profile.surname}</p>
            </div>
        </div>
        <div class="ml-4 rounded-lg w-full bg-slate-200">
            <p class="text-2xl text-orange-500 p-4">
                Описание:
            </p>
            <p class="text-xl p-4">
                {profile.moDo}
            </p>
        </div>
        </div>
    </div>
    {/if}
    {#if onError != null}
        <div class="flex flex-col pt-32">
            <div class="flex flex-col justify-center p-16 bg-slate-500 self-center rounded-2xl">
                <p class="text-3xl self-center text-orange-600">403</p>
                <p class="text-xl pt-16 self-center text-slate-300">Что-то пошло не так</p>
            </div>
        </div>
    {/if}
</div>