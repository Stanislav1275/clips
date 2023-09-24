<script>

    export let data;

    console.log(data);
    let onRender = false;
    let categories = data.categories;


    let id = data.id;

    $: categoryId = subcategories[0].category;
    let subcategories = updateSubcategories(categoryId);
    
    let offers = [];
    $: currOffers = offers;

    function updateSubcategories(id){
        onRender = false;
        let res = [];
        for(let subcategory of data.subcategories){
            if(id == subcategory.category){
                res.push(subcategory);
            }
        }

        onRender = true;
        return res;
    }
</script>

<div>
    {#if onRender}
    <div class="mt-4 pt-4 h-full bg-slate-600 rounded-2xl">
        <div class="grid grid-cols-6 pl-4 pr-4 pb-4">
            {#each categories as category}
                <div class="flex rounded-xl text-sm mr-2 py-2 justify-center align-middle {categoryId == category.id ? 'bg-orange-400' : 'bg-slate-700'}">
                    <a class="{categoryId == category.id ? "text-slate-700": "text-slate-200"}" href="/freelance/category/{category.id}">
                        {category.name}
                    </a>
                </div>
            {/each}
        </div>
    </div>
    <div class="py-2 grid grid-cols-5 bg-slate-600 mt-2 rounded-2xl">
        {#each subcategories as subcategory}
            <div class="flex justify-center m-2 align-middle bg-slate-700 rounded-xl">
                <a class="flex flex-row self-center text-sm p-2 mx-2 justify-center text-slate-200" href="/freelance/subcategory/{subcategory.id}">
                    {subcategory.name}
                </a>
            </div>
        {/each}
    </div>
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
    {/if}
</div>
