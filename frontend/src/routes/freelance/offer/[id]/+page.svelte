<script>
	import { onMount } from 'svelte';
    import { myToken } from '$lib/components/stores.js';

    export let data;

    let token;
    let offerID = data.offerID;
    let offer;
    let profile;
    let tiers;
    let onUpdate = true;
    let onRender = false;
    $: if(!onUpdate){
        onRender = true;
    }
    let opTok = "";
    let addTok = "";
    let chosenTier = null;

    if(token != null && token.publicPart != null){
        opTok = "?TOKEN=" + token.publicPart;
        addTok = "&TOKEN=" + token.publicPart;
    }

    myToken.subscribe((value) => {token = value});
    onMount(()=>{
            if(onUpdate == true){
            getData().then( value=>{
                onUpdate = false;
            });
        }
    })
    
    async function getData(){
        const offerResponse = await fetch(`http://127.0.0.1:8080/offer/${offerID + opTok}`, { method: "GET", mode: 'cors'});
        offer = await offerResponse.json();
        
        const profileResponse = await fetch(`http://127.0.0.1:8080/profile/${offer.creator + opTok}`, { method: "GET", mode: 'cors'});
        profile = await profileResponse.json();

        const tiersResponse = await fetch(`http://127.0.0.1:8080/job_tier?ID=${offerID + addTok}`, { method: "GET", mode: 'cors'});
        tiers = await tiersResponse.json();
        
    
    }
    async function placeOrder(id){
        
    }
    
</script>

<div>
    {#if onRender == true}
        <div class="flex flex-row h-full rounded-lg my-6 bg-slate-500">
            <div class="rounded-2xl w-auto p-2 m-4 flex flex-col bg-slate-300 align-top h-auto">
                <img class="bg-slate-300" src="http://127.0.0.1:8080/uploads/{profile.profilePic}">
                <p>{profile.name}</p>
                <p>{profile.surname}</p>
            </div> 
            <div class="flex flex-row align-middle justify-around pt-12 w-full">
                <div class="flex flex-col justify-evenly self-center">
                    <p class="text-3xl pb-12">
                        {offer.title}
                    </p>
                    <p class="text-xl">
                        {offer.text}
                    </p>
                </div>
                <div class="flex flex-col">
                    {#each tiers as tier}
                    <div class="w-full flex flex-row mr-6 mb-4 self-center justify-center bg-white rounded-2xl">
                        <div on:click={() => chosenTier = tier} class="rounded-2xl ml-2 h-8 w-8 self-center {chosenTier === tier ? 'bg-orange-400' : 'bg-slate-600'}">
                        </div>
                        <div class="w-full">
                            <p class="text-md p-2">
                                {tier.name}
                            </p>
                            <p class="text-lg p-2 text-orange-400">
                                {tier.cost}
                            </p>
                        </div>
                    </div>
                    {/each}
                </div>
                
            </div>
        </div>
        {#if chosenTier != null}
            <div class="flex flex-col">
                <div class="flex flex-row h-full rounded-lg my-6 bg-slate-500">
                    <div class="p-2 flex flex-col">
                        <p class="text-2xl text-orange-500">
                            {chosenTier.name}
                        </p>
                        <p class="text-xl text-slate-200">
                            {chosenTier.description}
                        </p>
                    </div>
                </div>
                <div class="flex flex-row justify-around">
                    {#if token != null}
                        <button on:click={placeOrder}>
                            Заказать
                        </button>
                    {:else}
                        <p class="text-lg">Для продолжения необходимо авторизоваться</p>
                        <button class="bg-orange-400 p-2 rounded-xl"><a href="/user">Войти</a></button>
                    {/if}
                    
                </div>
            </div>
            

        {/if}
    {/if}
</div>