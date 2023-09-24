<script>
	import { myToken, subcategory as subcategoryStore } from "$lib/components/stores";
	import { error } from "@sveltejs/kit";
    import Profile from "$lib/components/user/offer/profile.svelte";


    let offers = null;
    let prevSubcategory = null;
    $: subcategory = null;
    $: token = null;
    let tokenSub = myToken.subscribe( value => { token = value; } )
    let subcategorySub = subcategoryStore.subscribe( value => { subcategory = value; });
    $: if(subcategory !== null && prevSubcategory !== subcategory){
        if(token == null) getOffers("SUBCATEGORY=" + subcategory).then((value) => { offers = value; });
        else getOffers("SUBCATEGORY=" + subcategory + "&TOKEN=" + token.publicPart).then((value) => { offers = value; });
        prevSubcategory = subcategory;
    }

    async function getOffers(uri){
        const response = await fetch(`http://127.0.0.1:8080/offer?${uri}`, { method : "GET" });
        if(response.ok) {
            let res = await response.json();
            return res;
        }
        throw new error(response.status, response.statusText);
    }
</script>
<script context="module">

</script>


<div>
    {#if offers !== null && offers.length != 0} 
        <div class="flex flex-col bg-slate-400 rounded-md my-4 mx-auto p-4">
            <div class="grid grid-cols-5">
                {#each offers as offer}
                    <div class="w-auto h-auto m-2 rounded-2xl justify-center align-middle bg-slate-700">
                        <a href = {`/freelance/offer/${offer.id}`}>
                            <Profile user={offer.creator} />
                            <p class="flex p-4 align-middle text-slate-100">{offer.title}</p>    
                        </a>
                    </div>
                {/each}
            </div>
        </div>
    {/if}
</div>