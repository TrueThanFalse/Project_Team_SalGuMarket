console.log("SafeTrade.js Join Success");

if (typeof window.ethereum !== 'undefined') { // MetaMask 연동 확인    
    console.log('It is connected to MetaMask!');
} else { // MetaMask와 연결이 안된 상태면 MetaMask 설치 웹페이지로 새로운 창을 오픈
    alert('Please install MetaMask to use this feature.');
	window.history.back();
    window.open('https://chromewebstore.google.com/detail/metamask/nkbihfbeogaeaoehlefnkodbefgpgknn?hl=ko', '_blank');
}

const web3 = new Web3(window.ethereum); // MetaMask와 상호작용하는 Web3 객체 생성
const contractAddress = "0xafD6F07b7Fee71B0A1d05643a3e440ac1b9147Fb";
// 이더리움 네트워크에 배포된 스마트 계약의 주소
// <<< 주의 사항 >>> : Ganache에서 새로운 Workspace에 스마트 계약을 배포할 때마다 값을 수정해야 함
const contractABI = [
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "id",
				"type": "uint256"
			},
			{
				"indexed": false,
				"internalType": "address",
				"name": "approver",
				"type": "address"
			}
		],
		"name": "CancellationApproved",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "id",
				"type": "uint256"
			},
			{
				"indexed": false,
				"internalType": "address",
				"name": "requester",
				"type": "address"
			}
		],
		"name": "CancellationRequested",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "id",
				"type": "uint256"
			},
			{
				"indexed": false,
				"internalType": "address",
				"name": "seller",
				"type": "address"
			},
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "price",
				"type": "uint256"
			}
		],
		"name": "ProductCreated",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "id",
				"type": "uint256"
			},
			{
				"indexed": false,
				"internalType": "address",
				"name": "buyer",
				"type": "address"
			},
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "price",
				"type": "uint256"
			}
		],
		"name": "ProductPurchased",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "id",
				"type": "uint256"
			},
			{
				"indexed": false,
				"internalType": "address",
				"name": "buyer",
				"type": "address"
			}
		],
		"name": "PurchaseCancelled",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "id",
				"type": "uint256"
			},
			{
				"indexed": false,
				"internalType": "address",
				"name": "buyer",
				"type": "address"
			}
		],
		"name": "PurchaseConfirmed",
		"type": "event"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "_id",
				"type": "uint256"
			}
		],
		"name": "approveCancel",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"name": "buyers",
		"outputs": [
			{
				"internalType": "address",
				"name": "",
				"type": "address"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"name": "cancelRequests",
		"outputs": [
			{
				"internalType": "bool",
				"name": "requested",
				"type": "bool"
			},
			{
				"internalType": "bool",
				"name": "approved",
				"type": "bool"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "_id",
				"type": "uint256"
			}
		],
		"name": "confirmPurchase",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "_price",
				"type": "uint256"
			}
		],
		"name": "createProduct",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "productCount",
		"outputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"name": "products",
		"outputs": [
			{
				"internalType": "uint256",
				"name": "id",
				"type": "uint256"
			},
			{
				"internalType": "address payable",
				"name": "seller",
				"type": "address"
			},
			{
				"internalType": "uint256",
				"name": "price",
				"type": "uint256"
			},
			{
				"internalType": "bool",
				"name": "isSold",
				"type": "bool"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "_id",
				"type": "uint256"
			}
		],
		"name": "purchaseProduct",
		"outputs": [],
		"stateMutability": "payable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "_id",
				"type": "uint256"
			}
		],
		"name": "requestCancel",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "_id",
				"type": "uint256"
			}
		],
		"name": "sellerCancel",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	}
];

// 스마트 계약 인스턴스 생성
const contract = new web3.eth.Contract(contractABI, contractAddress);

// MetaMask와 연동된 계정 요청
async function getAccount() {
    const accounts = await ethereum.request({ method: 'eth_requestAccounts' });
    return accounts[0]; // 현재 내 계정을 반환
}

// 참고 사항 : 모든 거래 단위는 ether 단위로 통일

// 판매자의 제품 판매 등록
document.getElementById('createProduct').addEventListener('click', async () => {
    const account = await getAccount();

	// 웹사이트 로그인 사용자의 지갑 주소가 현재 MetaMask의 지갑 주소와 동일한지 확인
	if(loginUserWalletAddress == account){ 
		const price = web3.utils.toWei(document.getElementById('createProductVal').value, 'ether');
		// 사용자의 입력을 받아야 함

		contract.methods.createProduct(price).send({ from: account })
			.on('receipt', function(receipt){
				// 성공 상황
				console.log('Product created:', receipt);
			})
			.on('error', function(error) {
				// 에러 상황
				console.error('Transaction failed:', error)
				window.location.href = 'index.html';
			});
	}else{
		alert("MetaMask의 지갑 주소가 로그인 사용자의 지갑 주소와 일치하지 않습니다.");
	}
});

document.getElementById('purchaseProduct').addEventListener('click', async () => {
    const account = await getAccount();
	
	if(loginUserWalletAddress == account){
		const productId = document.getElementById('purchaseProductVal').value;
		const price = web3.utils.toWei(document.getElementById('purchaseProductPrice').value, 'ether');
		// 제품 번호와 가격 정보가 HTML에 전송되어 있어야 함
		
		contract.methods.purchaseProduct(productId).send({ from: account, value: price })
			.on('receipt', function(receipt){
				console.log('Product purchased:', receipt);
			})
			.on('error', function(error) {
				console.error('Transaction failed:', error)
				window.location.href = 'index.html';
			});
	};
});

document.getElementById('confirmPurchase').addEventListener('click', async () => {
    const account = await getAccount();

	if(loginUserWalletAddress == account){
		const productId = document.getElementById('confirmPurchaseVal').value;
		contract.methods.confirmPurchase(productId).send({ from: account })
			.on('receipt', function(receipt){
				console.log('Purchase confirmed:', receipt);
			})
			.on('error', function(error) {
				console.error('Transaction failed:', error)
				window.location.href = 'index.html';
			});
	};
});

document.getElementById('requestCancel').addEventListener('click', async () => {
    const account = await getAccount();

	if(loginUserWalletAddress == account){
		const productId = document.getElementById('requestCancelVal').value;
		contract.methods.requestCancel(productId).send({ from: account })
			.on('receipt', function(receipt){
				console.log('Cancellation requested:', receipt);
			})
			.on('error', function(error) {
				console.error('Transaction failed:', error)
				window.location.href = 'index.html';
			});
	};
});

document.getElementById('approveCancel').addEventListener('click', async () => {
    const account = await getAccount();
	
	if(loginUserWalletAddress == account){
		const productId = document.getElementById('approveCancelVal').value;
		contract.methods.approveCancel(productId).send({ from: account })
			.on('receipt', function(receipt){
				console.log('Cancellation approved:', receipt);
			})
			.on('error', function(error) {
				console.error('Transaction failed:', error)
				window.location.href = 'index.html';
			});
	};
});

document.getElementById('sellerCancel').addEventListener('click', async () => {
    const account = await getAccount();

	if(loginUserWalletAddress == account){
		const productId = document.getElementById('sellerCancelVal').value;
        contract.methods.sellerCancel(productId).send({ from: account })
        .on('receipt', function(receipt){
            console.log('Cancellation approved:', receipt);
        })
		.on('error', function(error) {
			console.error('Transaction failed:', error)
			window.location.href = 'index.html';
		});
	};
});

// 로그인된 유저는 HTML에서 가져올 수 있으므로 비동기통신으로 서버와 여러번 통신할 필요 없다.
/*
async function getLoginUserWalletAddress(account) {
    const url = '/smartContract/getLoginUserWalletAddress';
    const config = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify({account})
    };

    const response = await fetch(url, config);
    const result = await response.text();
    console.log("getLoginUserWalletAddress => ", result);
    return result;
}
*/