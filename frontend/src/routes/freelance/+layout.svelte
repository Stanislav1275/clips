<script>
    import { subcategory as subcategoryStore }  from '$lib/components/stores.js';

    export let data;
    const categories = data.categories;
    for(let category of categories){
        category.subcategories = [];
        for(let subcategory of data.subcategories){
            if(subcategory.category === category.id)category.subcategories.push(subcategory);
        }
    }
    let functionCallRes = null;
    let currState = null;
    if(currState === null) subcategoryStore.set(null);
</script>

<div>
    <div class="flex flex-col mb-4 bg-slate-700 rounded-3xl">
        <ul class="flex flex-col">
            <div class="flex flex-row bg-slate-700 py-2 rounded-3xl">
                {#each categories as category}
                    <li class="px-4"><button class="rounded-xl p-2 bg-orange-300" on:click={() => { functionCallRes = category.subcategories }}>{category.name}</button></li>
                {/each}
            </div>
            <div class="bg-slate-700 rounded-3xl">
                {#if functionCallRes !== null}
                    <ul class="flex flex-row rounded-2xl pb-2">
                    {#each functionCallRes as subcategory}
                        <li class="px-4">
                           <button on:click={()=> {currState = subcategory.id; subcategoryStore.set(subcategory.id);}} class="rounded-xl p-2 bg-orange-100">{subcategory.name}</button>
                        </li>
                    {/each}
                    </ul>
                {/if}
            </div>
            
        </ul>
    </div>
    <slot/>
</div>
